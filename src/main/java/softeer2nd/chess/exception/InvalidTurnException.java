package softeer2nd.chess.exception;

public class InvalidTurnException extends RuntimeException {
    public static final String WHITE_TURN_MESSAGE = "흰색이 두어야 할 차례입니다!";
    public static final String BLACK_TURN_MESSAGE = "검은색이 두어야 할 차례입니다!";

    public InvalidTurnException(String message) {
        super(message);
    }
}
