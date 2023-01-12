package edu.brown.cs32.chessish.board;

import edu.brown.cs32.chessish.game.Player;
import edu.brown.cs32.chessish.pieces.PieceType;

/**
 * A specific piece. Note that the location of this piece is managed by the Board, not the Piece itself.
 */
public record Piece(PieceType pieceType, Player player){
    public String oneChar() {
        return player.represent(pieceType.symbol());
    }
}
