package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PawnTest {

    private Piece pawn;

    @Test
    @DisplayName("시작 위치의 폰은 다른 파일(열)로 이동할 수 없다.")
    void pawn_move_file() {

        //given
        String sourcePos = "b2";
        String targetPos = "a3";
        Position sourcePosition = new Position(sourcePos);
        Position targetPosition = new Position(targetPos);
        pawn = Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN, sourcePosition);

        //when,then
        assertThatThrownBy(() -> pawn.verifyMovePosition(sourcePosition, targetPosition))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("폰은 다른 파일로 이동할 수 없습니다!");
    }

    @Test
    @DisplayName("시작 위치의 폰은 최대 두 칸 이동할 수 있다.")
    public void pawn_move_max() {

        //given
        Position sourcePosition = new Position("b2");
        Position targetPosition = new Position("b5");
        pawn = Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN, sourcePosition);

        //when,then
        assertThatThrownBy(() -> pawn.verifyMovePosition(sourcePosition, targetPosition))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("시작 위치의 폰은 최대 두 칸 이동할 수 있습니다!");

    }

    @ParameterizedTest
    @ValueSource(strings = {"c6", "e6", "d7", "d4"})
    @DisplayName("시작 위치의 폰이 아니라면 진행 방향으로 한 칸만 이동할 수 있다.(예외처리)")
    public void pawn_move_fail(String targetPos) {

        //given
        Position sourcePosition = new Position("d5");
        Position targetPosition = new Position(targetPos);
        pawn = Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN, sourcePosition);

        //when,then
        assertThatThrownBy(() -> pawn.verifyMovePosition(sourcePosition, targetPosition))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("폰의 이동이 올바르지 않습니다!");
    }

    @Test
    @DisplayName("시작 위치의 폰이 아니라면 진행 방향으로 한 칸만 이동할 수 있다.(정상작동)")
    public void pawn_move_success() {

        //given
        Position sourcePosition = new Position("d5");
        Position targetPosition = new Position("d6");
        pawn = Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN, sourcePosition);

        //when,then
        assertDoesNotThrow(() -> pawn.verifyMovePosition(sourcePosition, targetPosition));
    }

}