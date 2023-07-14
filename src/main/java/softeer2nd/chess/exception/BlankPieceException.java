package softeer2nd.chess.exception;

public class BlankPieceException extends RuntimeException {
    public static final String PIECE_DOES_NOT_EXISTS = "말이 존재하지 않는 위치입니다.";
    public BlankPieceException(String message) {
        super(message);
    }
}
