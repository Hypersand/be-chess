package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("팩토리 메서드로 기물 생성 시 정상적으로 만들어져야 한다.")
    public void create_piece() {
        verifyPiece(Piece.createPiece(Color.WHITE, Type.PAWN, new Position("a2")), Piece.createPiece(Color.BLACK, Type.PAWN, new Position("a3")), Type.PAWN);
        verifyPiece(Piece.createPiece(Color.WHITE, Type.KNIGHT, new Position("c3")), Piece.createPiece(Color.BLACK, Type.KNIGHT, new Position("d3")), Type.KNIGHT);
        verifyPiece(Piece.createPiece(Color.WHITE, Type.ROOK, new Position("e3")), Piece.createPiece(Color.BLACK, Type.ROOK, new Position("f3")), Type.ROOK);
        verifyPiece(Piece.createPiece(Color.WHITE, Type.BISHOP, new Position("a6")), Piece.createPiece(Color.BLACK, Type.BISHOP,new Position("b6")), Type.BISHOP);
        verifyPiece(Piece.createPiece(Color.WHITE, Type.KING, new Position("a1")), Piece.createPiece(Color.BLACK, Type.KING, new Position("a5")), Type.KING);
        verifyPiece(Piece.createPiece(Color.WHITE, Type.QUEEN, new Position("g3")), Piece.createPiece(Color.BLACK, Type.QUEEN, new Position("g6")), Type.QUEEN);

        Piece blank = Piece.createPiece(Color.NOCOLOR, Type.NO_PIECE, new Position("d4"));
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
        Piece piece = Piece.createPiece(Color.BLACK, Type.KING, new Position("c3"));
        assertTrue(piece.isBlack());
        assertFalse(piece.isWhite());
    }

    @Test
    @DisplayName("Piece가 흰색 말인지 구분할 수 있어야 한다.")
    public void isWhite() {
        Piece piece = Piece.createPiece(Color.WHITE, Type.QUEEN, new Position("d4"));
        assertTrue(piece.isWhite());
        assertFalse(piece.isBlack());
    }

    @Test
    @DisplayName("Type과 Color 지정 시 정상적으로 representation 되어야 한다.")
    public void getRepresentationPerPiece() throws Exception {

        //given,when
        Piece whitePawn = Piece.createPiece(Color.WHITE, Type.PAWN, new Position("a1"));
        Piece blackPawn = Piece.createPiece(Color.BLACK, Type.PAWN, new Position("b1"));

        //then
        assertEquals('p', whitePawn.getRepresentation());
        assertEquals('P', blackPawn.getRepresentation());
    }

}