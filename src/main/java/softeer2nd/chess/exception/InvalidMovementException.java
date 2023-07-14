package softeer2nd.chess.exception;

public class InvalidMovementException extends RuntimeException {
    public static final String PATH_OBSTRUCTED_MESSAGE = "배치하려는 위치의 경로 중간에 말이 있으면 이동할 수 없습니다!";
    public static final String PAWN_MOVE_DIAGONAL_MESSAGE = "폰은 기물이 없는 곳으로 대각선 이동할 수 없습니다!";
    public static final String PAWN_MOVE_STRAIGHT_MESSAGE = "폰은 기물이 있는 곳으로 직선 이동할 수 없습니다!";
    public InvalidMovementException(String message) {
        super(message);
    }
}
