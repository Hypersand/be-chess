package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;
import softeer2nd.utils.Direction;

public class Bishop extends Piece{
    private Bishop(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static Bishop createWhiteBishop(Position position) {
        return new Bishop(Color.WHITE, Type.BISHOP, position);
    }

    public static Bishop createBlackBishop(Position position) {
        return new Bishop(Color.BLACK, Type.BISHOP, position);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {
        int xDist = targetPosition.getFile() - sourcePosition.getFile();
        int yDist = targetPosition.getRank() - sourcePosition.getRank();

        if (Math.abs(xDist) / Math.abs(yDist) == 1) {
            return;
        }

        throw new InvalidMovementException("비숍의 이동이 올바르지 않습니다!");
    }
}
