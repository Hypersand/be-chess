package softeer2nd.chess.exception;

public class InvalidPositionException extends RuntimeException{

    public InvalidPositionException(String message) {
        super(message);
    }
}
