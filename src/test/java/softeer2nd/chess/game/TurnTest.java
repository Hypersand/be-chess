package softeer2nd.chess.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TurnTest {

    @Test
    @DisplayName("턴 객체를 만들면 흰색 턴이며 첫번째 턴임을 확인해야 한다.")
    void turn_create() {

        //when
        Turn turn = new Turn();

        //then
        assertTrue(turn.isFirstTurn());
        assertTrue(turn.isWhiteTurn());
    }

    @Test
    @DisplayName("첫번째 턴이 종료되면 isFirstTurn을 false로 바꾼다.")
    void first_turn_end() {

        //given
        Turn turn = new Turn();

        //when
        turn.changeFirstTurn();

        //then
        assertFalse(turn.isFirstTurn());
    }

    @Test
    @DisplayName("턴이 종료되면 isWhiteTurn을 반대로 바꾼다.")
    void turn_end() {

        //given
        Turn turn = new Turn();

        //when
        turn.changeTurn();

        //then
        assertFalse(turn.isWhiteTurn());
    }


}