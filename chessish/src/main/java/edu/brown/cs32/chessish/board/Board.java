package edu.brown.cs32.chessish.board;

import edu.brown.cs32.chessish.game.Player;
import edu.brown.cs32.chessish.pieces.PieceType;

import java.util.*;

/**
 * Representation of the board state in an ongoing game of chess-ish.
 */
public class Board {
    /**
     * We're assuming here that each location can only hold a single piece at once. This is reasonable for a prototype,
     * but of course isn't true for more complex games
     */
    private final Piece[][] board;

    /** The players of the game, in order */
    private final List<Player> players;

    /** Whose turn is it right now? */
    private Player turn;

    /** The types of pieces in play for this game */
    private final Set<PieceType> pieceTypes;

    /** The number of Action objects taken */
    private int turnCount = 0;

    public Board(int size, List<Player> players, Set<PieceType> pieceTypes) {
        if(players.size() < 1)
            throw new IllegalArgumentException("The game must have at least one player.");

        // Create a new (size) X (size) array to store the board state.
        this.board = new Piece[size][size];

        // Design note: this is called a _defensive copy_: we've been given a list by whoever called this method,
        // and they might accidentally modify it in the future. We don't want the caller to be able to change the
        // players (or their ordering)! So we store the player objects in a fresh list.
        this.players = new ArrayList<>(players);
        // Same for the piece types
        this.pieceTypes = new HashSet<>(pieceTypes);

        // The first player goes first.
        this.turn = players.get(0);
    }

    /**
     * Which actions are available to a player at this time, if they were to move?
     * @return a set of Action objects that represent all possible actions to take
     */
    public Set<Action> availableActions(Player player) {
        final Set<Action> result = new HashSet<>();

        // Moving an existing piece: check all locations on the board for a piece
        for(int row=0;row<board.length;row++) {
            for(int col=0;col<board[row].length; col++) {
                Piece here = board[row][col];
                if(here != null && here.player() == player)
                    result.addAll(here.pieceType().moves(this, new Position(col, row), player));
            }
        }

        return result;
    }

    /** Relocations queued for atomic execution */
    private final Map<Position, Position> relocateQueue = new HashMap<>();
    /** Removals queued for atomic execution */
    private final Set<Position> removePieceQueue = new HashSet<>();
    /** New piece placements for atomic execution */
    private final Map<Position, Piece> placeQueue = new HashMap<>();

    /**
     * Executes the relocation of a piece at `current` to new location `target`
     * @param current Position of the piece to relocate
     * @param target Position of the piece after relocation
     * @return the Piece object in the target location, if any
     * @throws IllegalAction if no piece is present at the given location
     */
    Piece relocate(Position current, Position target) throws IllegalAction {
        if(board[current.row()][current.column()] == null)
            throw new IllegalAction("No piece at that location.", current);
        Piece piece = board[current.row()][current.column()];
        Piece former = board[target.row()][target.column()];
        board[target.row()][target.column()] = piece;
        board[current.row()][current.column()] = null;
        return former;
    }

    /**
     * Executes the capture of a piece at `current` location
     * @param pos Position of the piece to remove from the board
     * @return the Piece object removed from the board, if any
     * @throws IllegalAction if no piece is present at the given location
     */
    Piece removePiece(Position pos) throws IllegalAction {
        if(board[pos.row()][pos.column()] == null)
            throw new IllegalAction("No piece at that location.", pos);
        Piece piece = board[pos.row()][pos.column()];
        board[pos.row()][pos.column()] = null;
        return piece;
    }

    /**
     * Place a piece at a given location on the board
     * @param pos the location
     * @param piece the piece to place
     * @throws IllegalAction if there is already a piece at that location
     */
    void place(Position pos, Piece piece) throws IllegalAction {
        if(board[pos.row()][pos.column()] != null)
            throw new IllegalAction("Piece already at that location.", pos);
        board[pos.row()][pos.column()] = piece;
    }

    /**
     * Queue a relocation for atomic execution.
     * @param current Relocate from
     * @param target Relocate to
     * @return contains the target of a previously registered relocation for this current position, if any
     */
    Position queueRelocate(Position current, Position target) {
        return relocateQueue.put(current, target);
    }

    /**
     * Queue a removal for atomic execution
     * @param pos remove piece from
     * @return true if and only if the position was not already queued for removal
     */
    boolean queueRemovePiece(Position pos) {
        return removePieceQueue.add(pos);
    }

    /**
     * Queue placement of a new piece
     * @param current location of the piece
     * @param piece the piece to place
     */
    public void queuePlace(Position current, Piece piece) {
        placeQueue.put(current, piece);
    }


    /**
     * Execute the current queue of board operations. If any is illegal, the batch will be rolled back.
     *
     * @throws IllegalAction if any operation had to be forbidden
     */
    public void runQueue() throws IllegalAction {
        Map<Position, Piece> removeRollbacks = new HashMap<>();
        Map<Position, Position> relocateRollbacksTarget = new HashMap<>();
        Map<Position, Piece> relocateRollbacksFormerPiece = new HashMap<>();
        Map<Position, Piece> placeRollbacks = new HashMap<>();

        try {
            for (Position pos : this.removePieceQueue) {
                Piece piece = this.removePiece(pos);
                removeRollbacks.put(pos, piece);
            }
            for(Position current : this.relocateQueue.keySet()) {
                Position target = relocateQueue.get(current);
                Piece former = this.relocate(current, target);
                relocateRollbacksTarget.put(current, target);
                relocateRollbacksFormerPiece.put(target, former);
            }
            for(Position pos : this.placeQueue.keySet()) {
                Piece newPiece = placeQueue.get(pos);
                this.place(pos, newPiece);
                placeRollbacks.put(pos, newPiece);
            }
            this.clearQueue(); // done

        } catch(IllegalAction e) {

            // Design consideration: is it important to perform these operations in reverse? (When, if at all?)

            for(Position current : relocateRollbacksTarget.keySet()) {
                Position target = relocateRollbacksTarget.get(current);
                Piece former = relocateRollbacksFormerPiece.get(target);
                // Fail noisily if our assumptions are incorrect.
                if(board[target.row()][target.column()] == null)
                    throw new IllegalStateException("Expected a piece at this location.");
                if(board[current.row()][current.column()] != null)
                    throw new IllegalStateException("Expected no piece at this location.");
                board[current.row()][current.column()] = board[target.row()][target.column()];
                board[target.row()][target.column()] = former; // may be null
            }
            for(Position pos : removeRollbacks.keySet()) {
                // Fail noisily if our assumptions are incorrect.
                if(board[pos.row()][pos.column()] != null)
                    throw new IllegalStateException("Expected no piece at this location.");
                board[pos.row()][pos.column()] = removeRollbacks.get(pos);
            }
            for(Position pos : placeRollbacks.keySet()) {
                // Fail noisily if our assumptions are incorrect.
                if(board[pos.row()][pos.column()] == null)
                    throw new IllegalStateException("Expected a piece at this location.");
                board[pos.row()][pos.column()] = null;
            }

            // Now that the prior state is restored, throw the exception for the caller to handle
            throw e;
        }
    }

    void clearQueue() {
        removePieceQueue.clear();
        relocateQueue.clear();
        placeQueue.clear();
    }

    /**
     * Get the player whose turn it currently is.
     * @return the player
     */
    public Player turn() {
        return turn;
    }

    public void endTurn() {
        turnCount++;
        int playerIdx = players.indexOf(turn);
        if(playerIdx+1 < players.size())
            turn = players.get(playerIdx+1);
        else
            turn = players.get(0);
    }

    /**
     * Returns the piece at a given position, if any
     * @param pos the target position
     * @return a Piece object or null
     */
    public Piece pieceAt(Position pos) {
        if(pos.row() < 0 || pos.column() < 0) return null;
        if(pos.row() >= 8 || pos.column() >= 8) return null;
        return board[pos.row()][pos.column()];
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("-----------------------"+turnCount+";"+turn+"--------------------------\n");
        for(int row=0;row<board.length;row++) {
            sb.append(row+": ");
            for(int col=0;col<board.length;col++) {
                Piece piece = board[row][col];
                if(piece == null)
                    sb.append(' ');
                else
                    sb.append(piece.oneChar());
            }
            sb.append("\n"); // new line
        }
        return sb.toString();
    }

}




