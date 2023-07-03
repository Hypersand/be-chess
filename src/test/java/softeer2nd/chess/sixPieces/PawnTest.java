package softeer2nd.chess.sixPieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다.")
    public void create() {
        Pawn pawn1 = new Pawn("white");
        Pawn pawn2 = new Pawn("black");
        assertThat(pawn1.getColor()).isEqualTo("white");
        assertThat(pawn2.getColor()).isEqualTo("black");

    }

}