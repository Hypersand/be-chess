package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다.")
    public void create() {
        final String WHITE = Pawn.WHITE_COLOR;
        final String BLACK = Pawn.BLACK_COLOR;

        verifyPawn(WHITE);
        verifyPawn(BLACK);
    }

    @Test
    @DisplayName("기본 생성자로 폰 생성 시 흰색 폰이 생성되어야 한다.")
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals("white", pawn.getColor());
    }

    private void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

}