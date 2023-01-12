package edu.brown.cs32.chessish.board;

/**
 * An action was attempted that is illegal according to the current Board.
 * The optional `pos` parameter should be used where possible to ease debugging.
 */
public class IllegalAction extends Exception {
    private Position pos;

    public IllegalAction(String message) {
        super(message);
    }
    public IllegalAction(String message, Position pos) {
        // Call this variant constructor
        this(message);
        this.pos = pos;
    }
    public Position pos() { return pos; }
}
