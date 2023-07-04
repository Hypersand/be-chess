package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    Board board;

    @BeforeEach
    public void createBoard() {
        board = new Board();
        board.initialize();
    }

    @Test
    public void create() throws Exception {

        board = new Board();
        for (int i = 0; i < 8; i++) {
            board.add(new ArrayList<>());
        }

        Pawn white = createPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        addToBoard(white);
        verifyBoard(white, 1, 1, 0);

        Pawn black = createPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
        addToBoard(black);
        verifyBoard(black, 1, 6, 0);
    }

    private Pawn createPawn(String color, char representation) {
        Pawn pawn = new Pawn(color, representation);
        return pawn;
    }

    private void addToBoard(Pawn pawn) {
        if (pawn.getColor().equals(Pawn.WHITE_COLOR)) {
            if (board.get(1).size() < 8) {
                board.get(1).add(pawn);
            }
        }

        else {
            if (board.get(6).size() < 8) {
                board.get(6).add(pawn);
            }
        }
    }

    private void verifyBoard(Pawn pawn, int size, int row, int col) {

        assertEquals(size, board.get(row).size());
        assertEquals(pawn, board.findPawn(row, col));
    }

    @Test
    public void initialize() throws Exception {

        assertEquals("pppppppp", board.getWhitePawnsResult());
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
    public void print() {

        String expectPrint = "........" + "\n"
                + "pppppppp" + "\n"
                + "........" + "\n"
                + "........" + "\n"
                + "........" + "\n"
                + "........" + "\n"
                + "PPPPPPPP" + "\n"
                + "........";
        assertEquals(expectPrint, board.toString());
    }

}