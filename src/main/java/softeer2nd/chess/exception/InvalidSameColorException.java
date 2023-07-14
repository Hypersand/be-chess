package softeer2nd.chess.exception;

public class InvalidSameColorException extends RuntimeException {
    public static final String SAME_COLOR_MOVE_MESSAGE = "같은 색 말 위로 이동할 수 없습니다!";
    public InvalidSameColorException(String message) {
        super(message);
    }
}
