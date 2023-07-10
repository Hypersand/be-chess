package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;
import softeer2nd.chess.exception.InvalidPositionException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KingTest {

    private Piece king;

    @ParameterizedTest
    @ValueSource(strings = {"b4", "b6", "a5", "c5", "a6", "c6", "a4", "c4"})
    @DisplayName("킹은 어느 방향으로든 한 칸을 움직일 수 있다.")
    void king_move(String targetPos) {

        //given
        String sourcePos = "b5";
        Position sourcePosition = new Position(sourcePos);
        Position targetPosition = new Position(targetPos);
        king = Piece.createPiece(Piece.Color.BLACK, Piece.Type.KING, sourcePosition);

        //when,then
        assertDoesNotThrow(() -> king.verifyMovePosition(sourcePosition, targetPosition));
    }

    @Test
    @DisplayName("체스판 밖으로 움직이면 예외 발생")
    public void piece_moveOut_board() {

        //given
        Position sourcePosition = new Position("e1");
        Position targetPosition = new Position("c0");
        king = Piece.createPiece(Piece.Color.BLACK, Piece.Type.KING, sourcePosition);


        //when,then
        assertThatThrownBy(() -> king.verifyTargetPosition(targetPosition))
                .isInstanceOf(InvalidPositionException.class)
                .hasMessage("체스판 위에 말을 배치해 주세요!");

    }

    @Test
    @DisplayName("킹이 한 칸 이상을 움직이면 예외 발생")
    public void king_move_exception() {

        //given
        Position sourcePosition = new Position("e1");
        Position targetPosition = new Position("e3");
        king = Piece.createPiece(Piece.Color.BLACK, Piece.Type.KING, sourcePosition);


        //when,then
        assertThatThrownBy(() -> king.verifyMovePosition(sourcePosition, targetPosition))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("킹의 이동이 올바르지 않습니다!");

    }
}