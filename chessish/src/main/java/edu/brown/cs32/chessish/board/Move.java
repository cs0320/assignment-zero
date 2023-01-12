package edu.brown.cs32.chessish.board;

import java.util.Set;

/**
 * An *intended* move. May or may not be legal. The `captures` field indicates which positions may be captured by
 * this piece in the process of the move.
 */
public record Move(Position current, Position target, Set<Position> captures) implements Action {

    /**
     * Execute this Move action vs. the given Board. To avoid inconsistent state change (e.g., what happens if
     * we run the relocate first, and then the removePieces, but the final removePiece fails?), queue all actions
     * into one atomic update. If the Board didn't support that, we'd need to manage this ourselves with try/catch.
     *
     * @param board current board state
     */
    @Override
    public void doAction(Board board) throws IllegalAction {
        board.queueRelocate(current, target);
        for (Position pos : captures) {
            board.queueRemovePiece(pos);
        }
        board.runQueue();
    }

    @Override
    public String toString() {
        return "move "+current+" to "+target;
    }
}
