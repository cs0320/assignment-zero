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
 * Test class for Board functionality
 *
 * Surely this isn't enough testing...?
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

    @Test
    public void testEmptySquare() {
        assertTrue(b.pieceAt(new Position(0,0)) == null);
    }
}
