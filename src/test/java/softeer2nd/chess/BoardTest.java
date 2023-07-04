package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;

    @BeforeEach
    public void createBoard() {
        board = new Board();
    }

    @Test
    public void create() throws Exception {

        Pawn white = createPawnAndAddToBoard(Pawn.WHITE_COLOR);
        verifyBoard(white, 1, 0);

        Pawn black = createPawnAndAddToBoard(Pawn.BLACK_COLOR);
        verifyBoard(black, 2, 1);
    }

    private Pawn createPawnAndAddToBoard(String color) {
        Pawn pawn = new Pawn(color);
        board.add(pawn);

        return pawn;
    }

    private void verifyBoard(Pawn pawn, int size, int index) {
        assertEquals(size, board.size());
        assertEquals(pawn, board.findPawn(index));
    }

}