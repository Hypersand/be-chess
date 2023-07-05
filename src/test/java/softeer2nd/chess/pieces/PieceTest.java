package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("팩토리 메서드로 기물 생성 시 정상적으로 만들어져야 한다.")
    public void create_piece() {
        verifyPiece(Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN), Piece.Color.WHITE, Piece.Type.PAWN);
        verifyPiece(Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN), Piece.Color.BLACK, Piece.Type.PAWN);
        verifyPiece(Piece.createPiece(Piece.Color.WHITE, Piece.Type.KNIGHT), Piece.Color.WHITE, Piece.Type.KNIGHT);
        verifyPiece(Piece.createPiece(Piece.Color.BLACK, Piece.Type.KNIGHT), Piece.Color.BLACK, Piece.Type.KNIGHT);
        verifyPiece(Piece.createPiece(Piece.Color.WHITE, Piece.Type.ROOK), Piece.Color.WHITE, Piece.Type.ROOK);
        verifyPiece(Piece.createPiece(Piece.Color.BLACK, Piece.Type.ROOK), Piece.Color.BLACK, Piece.Type.ROOK);
        verifyPiece(Piece.createPiece(Piece.Color.WHITE, Piece.Type.BISHOP), Piece.Color.WHITE, Piece.Type.BISHOP);
        verifyPiece(Piece.createPiece(Piece.Color.BLACK, Piece.Type.BISHOP), Piece.Color.BLACK, Piece.Type.BISHOP);
        verifyPiece(Piece.createPiece(Piece.Color.WHITE, Piece.Type.KING), Piece.Color.WHITE, Piece.Type.KING);
        verifyPiece(Piece.createPiece(Piece.Color.BLACK, Piece.Type.KING), Piece.Color.BLACK, Piece.Type.KING);
        verifyPiece(Piece.createPiece(Piece.Color.WHITE, Piece.Type.QUEEN), Piece.Color.WHITE, Piece.Type.QUEEN);
        verifyPiece(Piece.createPiece(Piece.Color.BLACK, Piece.Type.QUEEN), Piece.Color.BLACK, Piece.Type.QUEEN);
    }

    private void verifyPiece(final Piece piece, Piece.Color color, Piece.Type type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
    }

    @Test
    @DisplayName("Piece가 검은색 말인지 구분할 수 있어야 한다.")
    public void isBlack() {
        Piece piece = Piece.createPiece(Piece.Color.BLACK, Piece.Type.KING);
        assertTrue(piece.isBlack());
        assertFalse(piece.isWhite());
    }

    @Test
    @DisplayName("Piece가 흰색 말인지 구분할 수 있어야 한다.")
    public void isWhite() {
        Piece piece = Piece.createPiece(Piece.Color.WHITE, Piece.Type.QUEEN);
        assertTrue(piece.isWhite());
        assertFalse(piece.isBlack());
    }

    @Test
    public void getRepresentationPerPiece() throws Exception {
        assertEquals('p', Piece.Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Piece.Type.PAWN.getBlackRepresentation());
    }

}