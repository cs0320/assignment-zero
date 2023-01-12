package edu.brown.cs32.chessish.pieces;

import edu.brown.cs32.chessish.board.Place;
import edu.brown.cs32.chessish.board.Board;
import edu.brown.cs32.chessish.board.Move;
import edu.brown.cs32.chessish.board.Position;
import edu.brown.cs32.chessish.game.Player;

import java.util.Set;

/**
 * Chess-ish doesn't just have the standard pieces from chess. We want to build a library that enables other
 * programmers to define their own kinds of pieces. A king from chess would implement this interface, but so
 * would a stone from go.
 *
 * We are leaving out many possible features, like promotion of pawns.
 *
 */
public interface PieceType {
    /**
     * Computes the legal moves for a piece that already exists on the board.
     * Note that the board may itself impose additional constraints, e.g., disallowing two pieces to occupy the same space.
     * @param state current board state
     * @param current current piece position
     * @param player the player moving this piece
     * @return the set of legal creation actions for this piece.
     */
    Set<Move> moves(Board state, Position current, Player player);

    /**
     * A symbol to represent this piece type when printing the board
     * @return the pertinent string
     */
    String symbol();
}
