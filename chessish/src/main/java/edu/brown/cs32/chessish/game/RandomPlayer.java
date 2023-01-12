package edu.brown.cs32.chessish.game;

import edu.brown.cs32.chessish.board.Action;
import edu.brown.cs32.chessish.board.Board;
import edu.brown.cs32.chessish.main.Utils;

import java.util.Set;
import java.util.function.Function;

/**
 * A player who always makes a random move from those available.
 */
public class RandomPlayer extends Player {
    Function<String, String> pieceAnnotator;

    public RandomPlayer(String id, int side, Function<String, String> pieceAnnotator) {
        // Call the super-class's constructor (which populates the id field)
        super(id, side);
        this.pieceAnnotator = pieceAnnotator;
    }

    @Override
    public Action decide(Board board) {
        Set<Action> available = board.availableActions(this);
        if(available.size() < 1) return null; // no options left
        return Utils.randomMember(available);
    }

    @Override
    public String represent(String symbol) {
        return this.pieceAnnotator.apply(symbol);
    }

}
