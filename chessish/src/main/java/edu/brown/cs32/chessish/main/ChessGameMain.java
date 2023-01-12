package edu.brown.cs32.chessish.main;

import edu.brown.cs32.chessish.board.*;
import edu.brown.cs32.chessish.game.Player;
import edu.brown.cs32.chessish.game.RandomPlayer;
import edu.brown.cs32.chessish.pieces.Chess.Pawn;
import edu.brown.cs32.chessish.pieces.PieceType;

import java.util.List;
import java.util.Set;

/**
 * Main class for this application. Represents a game of Chess-ish.
 *
 */
public class ChessGameMain {
    /**
     * Should debugging info be printed? (There are more sophisticated ways to manage this, but for now it's
     * good enough!
     */
    public static boolean debugging = true;

    /**
     * Entry point for the application. (A project can have more than one class with a main method, if you
     * need multiple entry points; usually we just need one.)
     * @param args the command-line arguments given when we ran the program (if any)
     */
    public static void main(String[] args) {
        // Up player starts at rows 0 and 1, moves positive
        // Down player starts at rows 6 and 7, moves negative
        Player upPlayer = new RandomPlayer("UP", 1, String::toUpperCase);
        Player downPlayer = new RandomPlayer("DOWN", -1, String::toLowerCase);
        List<Player> players = List.of(
                upPlayer,
                downPlayer
        );
        Set<PieceType> pieceTypes = Set.of(
                Pawn.get()
                // For now, just pawns
                //,
//                new Knight(),
//                new Bishop(),
//                new Rook(),
//                new Queen(),
//                new King()
        );
        Board board = new Board(8, players, pieceTypes);

        // Set up the board according to normal chess rules
        for(int col=0;col<=7;col++) {
            board.queuePlace(new Position(col, 1), new Piece(Pawn.get(), upPlayer));
            board.queuePlace(new Position(col, 6), new Piece(Pawn.get(), downPlayer));
        }
        try {
            board.runQueue();
        } catch(IllegalAction e) {
            System.out.println("Error setting up board; exiting. Stack trace for debugging:");
            e.printStackTrace();
            System.exit(1);
        }

        while(true) {
            System.out.println(board);
            Player p = board.turn();
            Action action = p.decide(board);
            if(action == null) {
                System.out.println("Player "+p+" had no remaining legal moves. Game ending.");
                break;
            }
            try {
                if(debugging) System.out.println("Action for "+p+": "+action);
                action.doAction(board);
                board.endTurn();
            } catch (IllegalAction e) {
                System.out.println("Illegal action chosen; stopping the game. Context for debugging:");
                System.out.println("Position: "+e.pos());
                e.printStackTrace();
                System.exit(2);
            }
        }
    }
}
