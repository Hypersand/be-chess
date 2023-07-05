package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.utils.StringUtils.appendNewLine;

class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.initialize();
    }

    @Test
    public void create() throws Exception {
        assertEquals(64, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                        appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                        board.showBoard()
        );
    }



    @Test
    public void initialize() throws Exception {

        assertEquals("pppppppp",board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    public void getWhitePawnsResult() {

        String whitePawnsResult = board.getWhitePawnsResult();
        assertEquals(whitePawnsResult, "pppppppp");
    }

    @Test
    public void getBlackPawnsResult() {

        String blackPawnsResult = board.getBlackPawnsResult();
        assertEquals(blackPawnsResult, "PPPPPPPP");
    }

    @Test
    public void getWhitePiecesResult() {

        String piecesRepresentation = board.get(7).stream()
                .map(piece -> String.valueOf(piece.getType().getRepresentation(piece.getColor())))
                .collect(Collectors.joining());

        assertEquals(piecesRepresentation, "rnbqkbnr");
    }

    @Test
    public void getBlackPiecesResult() {

        String piecesRepresentation = board.get(0).stream()
                .map(piece -> String.valueOf(piece.getType().getRepresentation(piece.getColor())))
                .collect(Collectors.joining());

        assertEquals(piecesRepresentation, "RNBQKBNR");
    }

    @Test
    public void print() {

        String expectPrint = "RNBQKBNR" + "\n"
                + "PPPPPPPP" + "\n"
                + "........" + "\n"
                + "........" + "\n"
                + "........" + "\n"
                + "........" + "\n"
                + "pppppppp" + "\n"
                + "rnbqkbnr" + "\n";
        assertEquals(expectPrint, board.toString());
    }

}