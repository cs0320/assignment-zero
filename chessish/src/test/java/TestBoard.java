import edu.brown.cs32.chessish.board.Board;
import edu.brown.cs32.chessish.board.Position;
import edu.brown.cs32.chessish.game.Player;
import edu.brown.cs32.chessish.game.RandomPlayer;
import edu.brown.cs32.chessish.pieces.Chess.Pawn;
import edu.brown.cs32.chessish.pieces.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for Board functionality. I'm using JUnit version 5. See
 * pom.xml in the project root directory for more information. (If you
 * try this, and the tests run in IntelliJ but not in `maven` from the
 * command line, make sure you've got the surefire extension in pom.xml.)
 *
 * But surely this isn't enough testing...?
 */
public class TestBoard {

    Board b;
    Player upPlayer;
    Player downPlayer;

    /**
     * This method runs before every individual @Test method. In this way, we ensure that
     * there's no state kept around between tests.
     */
    @BeforeEach
    public void setup() {
        upPlayer = new RandomPlayer("UP", 1, String::toUpperCase);
        downPlayer = new RandomPlayer("DOWN", -1, String::toLowerCase);
        List<Player> players = List.of(upPlayer, downPlayer);
        Set<PieceType> pieceTypes = Set.of(Pawn.get());
        b = new Board(8, players, pieceTypes);
    }

    /**
     * JUnit will run @Test-annotated methods as test cases
     */
    @Test
    public void testEmptySquare() {
        assertTrue(b.pieceAt(new Position(0,0)) == null);
    }
}
