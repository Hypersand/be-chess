package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;
import softeer2nd.chess.exception.InvalidPositionException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    private Piece rook;

    @ParameterizedTest
    @ValueSource(strings = {"a5", "d7", "g5", "d4"})
    @DisplayName("룩은 상하좌우 일직선으로 원하는 만큼 이동할 수 있다.")
    void rook_move(String targetPos) {

        //given
        String sourcePos = "d5";
        Position sourcePosition = new Position(sourcePos);
        Position targetPosition = new Position(targetPos);
        rook = Piece.createPiece(Piece.Color.WHITE, Piece.Type.ROOK, sourcePosition);

        //when,then
        assertDoesNotThrow(() -> rook.verifyMovePosition(sourcePosition, targetPosition));
    }

    @Test
    @DisplayName("체스판 밖으로 움직이면 예외 발생")
    public void piece_moveOut_board() {

        //given
        Position sourcePosition = new Position("e1");
        Position targetPosition = new Position("c0");
        rook = Piece.createPiece(Piece.Color.BLACK, Piece.Type.QUEEN, sourcePosition);

        //when,then
        assertThatThrownBy(() -> rook.verifyTargetPosition(targetPosition))
                .isInstanceOf(InvalidPositionException.class)
                .hasMessage("체스판 위에 말을 배치해 주세요!");

    }

    @Test
    @DisplayName("룩의 올바른 이동 방향이 아니면 예외 발생")
    public void rook_move_exception() {

        //given
        Position sourcePosition = new Position("d4");
        Position targetPosition = new Position("e6");
        rook = Piece.createPiece(Piece.Color.BLACK, Piece.Type.ROOK, sourcePosition);

        //when,then
        assertThatThrownBy(() -> rook.verifyMovePosition(sourcePosition, targetPosition))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("룩의 이동이 올바르지 않습니다!");

    }

}