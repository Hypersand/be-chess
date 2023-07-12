package softeer2nd.chess.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TurnTest {

    @Test
    @DisplayName("턴 객체를 만들면 흰색 턴이다.")
    void turn_create() {

        //when
        Turn turn = new Turn();

        //then
        assertTrue(turn.isWhite());
    }

    @Test
    @DisplayName("턴이 종료되면 isWhiteTurn을 반대로 바꾼다.")
    void turn_end() {

        //given
        Turn turn = new Turn();

        //when
        turn.changeTurn();

        //then
        assertFalse(turn.isWhite());
    }


}