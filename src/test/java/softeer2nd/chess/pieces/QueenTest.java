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

class QueenTest {

    private Piece queen;

    @ParameterizedTest
    @ValueSource(strings = {"d5", "f6", "g4", "g1", "d2", "c3", "a4", "c5"})
    @DisplayName("퀸은 어느 방향으로든 일직선으로 원하는 만큼 이동할 수 있다.")
    void queen_move(String targetPos) {

        //given
        String sourcePos = "d4";
        Position sourcePosition = new Position(sourcePos);
        Position targetPosition = new Position(targetPos);
        queen = Piece.createPiece(Piece.Color.BLACK, Piece.Type.QUEEN, sourcePosition);

        //when,then
        assertDoesNotThrow(() -> queen.verifyMovePosition(sourcePosition, targetPosition));
    }

    @Test
    @DisplayName("퀸의 올바른 이동 방향이 아니면 예외 발생")
    public void queen_move_exception() {

        //given
        Position sourcePosition = new Position("d4");
        Position targetPosition = new Position("e6");
        queen = Piece.createPiece(Piece.Color.BLACK, Piece.Type.QUEEN, sourcePosition);

        //when,then
        assertThatThrownBy(() -> queen.verifyMovePosition(sourcePosition, targetPosition))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("퀸의 이동이 올바르지 않습니다!");

    }


}