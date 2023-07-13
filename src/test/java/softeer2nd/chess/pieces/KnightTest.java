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

class KnightTest {

    private Piece knight;

    @ParameterizedTest
    @ValueSource(strings = {"e6","f5","f3","e2","c2","b3","b5","c6"})
    @DisplayName("나이트는 현재 위치한 칸으로부터 같은 랭크, 파일, 대각에 위치하지 않은 칸들 중에서 현재 위치와 가장 가까운 칸으로 이동한다.")
    void knight_move(String targetPos) {

        //given
        String sourcePos = "d4";
        Position sourcePosition = new Position(sourcePos);
        Position targetPosition = new Position(targetPos);
        knight = Piece.createPiece(Piece.Color.WHITE, Piece.Type.KNIGHT, sourcePosition);

        //when,then
        assertDoesNotThrow(() -> knight.verifyMovePosition(sourcePosition, targetPosition));
    }

    @Test
    @DisplayName("나이트의 올바른 이동 방향이 아니면 예외 발생")
    public void knight_move_exception() {

        //given
        Position sourcePosition = new Position("d4");
        Position targetPosition = new Position("d6");
        knight = Piece.createPiece(Piece.Color.BLACK, Piece.Type.KNIGHT, sourcePosition);

        //when,then
        assertThatThrownBy(() -> knight.verifyMovePosition(sourcePosition, targetPosition))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("나이트의 이동이 올바르지 않습니다!");

    }

}