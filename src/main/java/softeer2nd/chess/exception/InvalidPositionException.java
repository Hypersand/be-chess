package softeer2nd.chess.exception;

public class InvalidPositionException extends RuntimeException {
    public static final String OUT_OF_BOUNDS_MESSAGE = "체스판 위에 말을 배치해 주세요!";
    public InvalidPositionException(String message) {
        super(message);
    }
}
