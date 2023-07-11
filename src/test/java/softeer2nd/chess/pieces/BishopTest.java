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

class BishopTest {

    private Piece bishop;

    @ParameterizedTest
    @ValueSource(strings = {"b7", "g8", "e4", "b3"})
    @DisplayName("비숍은 대각선 방향으로 기물이 없는 칸에 한해서 칸수의 제한 없이 움직일 수 있다.")
    void bishop_move(String targetPos) {

        //given
        String sourcePos = "d5";
        Position sourcePosition = new Position(sourcePos);
        Position targetPosition = new Position(targetPos);
        bishop = Piece.createPiece(Piece.Color.WHITE, Piece.Type.BISHOP, sourcePosition);

        //when,then
        assertDoesNotThrow(() -> bishop.verifyMovePosition(sourcePosition, targetPosition));
    }

    @Test
    @DisplayName("체스판 밖으로 움직이면 예외 발생")
    public void piece_moveOut_board() {

        //given
        Position sourcePosition = new Position("e1");
        Position targetPosition = new Position("c0");
        bishop = Piece.createPiece(Piece.Color.BLACK, Piece.Type.BISHOP, sourcePosition);

        //when,then
        assertThatThrownBy(() -> bishop.verifyTargetPosition(targetPosition))
                .isInstanceOf(InvalidPositionException.class)
                .hasMessage("체스판 위에 말을 배치해 주세요!");

    }

    @Test
    @DisplayName("비숍의 올바른 이동 방향이 아니면 예외 발생")
    public void bishop_move_exception() {

        //given
        Position sourcePosition = new Position("d5");
        Position targetPosition = new Position("e8");
        bishop = Piece.createPiece(Piece.Color.BLACK, Piece.Type.BISHOP, sourcePosition);

        //when,then
        assertThatThrownBy(() -> bishop.verifyMovePosition(sourcePosition, targetPosition))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("비숍의 이동이 올바르지 않습니다!");

    }

}