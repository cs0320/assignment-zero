package edu.brown.cs32.chessish.game;

import edu.brown.cs32.chessish.board.Action;
import edu.brown.cs32.chessish.board.Board;

/**
 * A player of chess-ish. Represents ownership of pieces, territory, etc. Also bridges the gap
 * between the abstract game and the actual player or AI.
 *
 * This class is abstract, meaning that we can't create objects that are just Players -- they need to be
 * some *kind* of player -- like a RandomPlayer -- and define a specific strategy for play.
 */
abstract public class Player {
    /** The ID of this player, for use in printing */
    private final String id;

    /** Which side of the board belongs to this player? Should be either -1 or 1 */
    private final int side;

    Player(String id, int side) {
        if(side != -1 && side != 1)
            throw new IllegalArgumentException("Side must be -1 or 1");

        this.id = id;
        this.side = side;
    }
    public String id() { return this.id; }
    public int side() { return this.side; }

    /** Decide on a specific move to take */
    abstract public Action decide(Board board);
    /** How to represent a piece with this symbol? */
    abstract public String represent(String symbol);

    @Override
    public String toString() {
        return id;
    }
}
