package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("팩토리 메서드로 기물 생성 시 정상적으로 만들어져야 한다.")
    public void create_piece() {
        verifyPiece(Piece.createPiece(Color.WHITE, Type.PAWN), Piece.createPiece(Color.BLACK, Type.PAWN), Type.PAWN);
        verifyPiece(Piece.createPiece(Color.WHITE, Type.KNIGHT), Piece.createPiece(Color.BLACK, Type.KNIGHT), Type.KNIGHT);
        verifyPiece(Piece.createPiece(Color.WHITE, Type.ROOK), Piece.createPiece(Color.BLACK, Type.ROOK), Type.ROOK);
        verifyPiece(Piece.createPiece(Color.WHITE, Type.BISHOP), Piece.createPiece(Color.BLACK, Type.BISHOP), Type.BISHOP);
        verifyPiece(Piece.createPiece(Color.WHITE, Type.KING), Piece.createPiece(Color.BLACK, Type.KING), Type.KING);
        verifyPiece(Piece.createPiece(Color.WHITE, Type.QUEEN), Piece.createPiece(Color.BLACK, Type.QUEEN), Type.QUEEN);

        Piece blank = Piece.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

    @Test
    @DisplayName("Piece가 검은색 말인지 구분할 수 있어야 한다.")
    public void isBlack() {
        Piece piece = Piece.createPiece(Color.BLACK, Type.KING);
        assertTrue(piece.isBlack());
        assertFalse(piece.isWhite());
    }

    @Test
    @DisplayName("Piece가 흰색 말인지 구분할 수 있어야 한다.")
    public void isWhite() {
        Piece piece = Piece.createPiece(Color.WHITE, Type.QUEEN);
        assertTrue(piece.isWhite());
        assertFalse(piece.isBlack());
    }

    @Test
    public void getRepresentationPerPiece() throws Exception {
        assertEquals('p', Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Type.PAWN.getBlackRepresentation());
    }

}