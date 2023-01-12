package edu.brown.cs32.chessish.pieces.Chess;

import edu.brown.cs32.chessish.board.*;
import edu.brown.cs32.chessish.game.Player;
import edu.brown.cs32.chessish.main.ChessGameMain;
import edu.brown.cs32.chessish.pieces.PieceType;

import java.util.HashSet;
import java.util.Set;

/**
 * A chess pawn. In chess, pieces need to be aware of directionality:
 *   - some (like a pawn) can move in a specific direction only
 */
public class Pawn implements PieceType {

    private static final Pawn singleton = new Pawn();
    private Pawn() {}

    @Override
    public Set<Move> moves(Board state, Position current, Player player) {
        Set<Move> results = new HashSet<>();
        Move move;

        int startingRow = player.side() > 0 ? 1 : 6;
        int oneForward = player.side() > 0 ? current.row()+1 : current.row()-1;
        int twoForward = player.side() > 0 ? current.row()+2 : current.row()-2;

        // Moving from starting position (may capture in passing)
        if(current.row() == startingRow) {
            // Don't try to capture something in passing if nothing is there
            Set<Position> threatened =
                    state.pieceAt(new Position(current.column(), oneForward)) != null ?
                            Set.of(new Position(current.column(), oneForward)) :
                            Set.of();

            move = new Move(current, new Position(current.column(), twoForward), threatened);
            results.add(move);
            if(ChessGameMain.debugging) { System.out.println("Pawn moves include: "+move); }
        }

        // Moving forward 1 (cannot capture, cannot move off the board)
        if(oneForward < 8 && oneForward > -1) {
            move = new Move(current, new Position(current.column(), oneForward), Set.of());
            results.add(move);
            if (ChessGameMain.debugging) {
                System.out.println("Pawn moves include: " + move);
            }
        }

        // Allowed to move diagonally if it will capture
        Piece left = state.pieceAt(new Position(current.column()+1, oneForward));
        Piece right = state.pieceAt(new Position(current.column()-1, oneForward));
        if(left != null && left.player() != player) {
            move = new Move(current, new Position(current.column()+1, oneForward),
                                          Set.of(new Position(current.column()+1, oneForward)));
            results.add(move);
            if(ChessGameMain.debugging) { System.out.println("Pawn moves include: "+move); }
        }
        if(right != null && right.player() != player) {
            move = new Move(current, new Position(current.column()-1, oneForward),
                            Set.of(new Position(current.column()-1, oneForward)));
            results.add(move);
            if(ChessGameMain.debugging) { System.out.println("Pawn moves include: "+move); }
        }

        return results;
    }

    @Override
    public String symbol() {
        return "P";
    }

    /**
     * We shouldn't ever need more than one object of this type. So we have the class itself manage one specific
     * object instance, which callers can obtain via this method
     * @return the canonical instance of the Pawn piece type
     */
    static public Pawn get() {
        return Pawn.singleton;
    }
}
