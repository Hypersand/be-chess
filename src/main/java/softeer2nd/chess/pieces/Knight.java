package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;
import softeer2nd.utils.Direction;

public class Knight extends Piece {

    private Knight(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static Knight createWhiteKnight(Position position) {
        return new Knight(Color.WHITE, Type.KNIGHT, position);
    }

    public static Knight createBlackKnight(Position position) {
        return new Knight(Color.BLACK, Type.KNIGHT, position);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {
        int xDist = targetPosition.getFile() - sourcePosition.getFile();
        int yDist = targetPosition.getRank() - sourcePosition.getRank();

        for (Direction direction : Direction.knightDirection()) {
            if (direction.getXDegree() == xDist && direction.getYDegree() == yDist) {
                return;
            }
        }

        throw new InvalidMovementException("나이트의 이동이 올바르지 않습니다!");
    }
}
