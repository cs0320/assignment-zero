package edu.brown.cs32.chessish.board;

import edu.brown.cs32.chessish.game.Player;

import java.util.Set;

/**
 * A *proposed* move that creates a new piece on the board. The `captures` field indicates which positions are
 * threatened (and may be captured) by the move.
 *
 * (Contrast this record vs. the Move record; when the piece doesn't exist
 */
public record Place(Piece newPiece, Position current, Set<Position> captures) implements Action {
    /**
     * Execute this Move action vs. the given Board. To avoid inconsistent state change (e.g., what happens if
     * we run the relocate first, and then the removePieces, but the final removePiece fails?), queue all actions
     * into one atomic update. If the Board didn't support that, we'd need to manage this ourselves with try/catch.
     *
     * @param board current board state
     */
    @Override
    public void doAction(Board board) throws IllegalAction {
        board.queuePlace(current, this.newPiece());
        for (Position pos : captures) {
            board.queueRemovePiece(pos);
        }
        board.runQueue();
    }
}
