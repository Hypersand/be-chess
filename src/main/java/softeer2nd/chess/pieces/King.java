package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;
import softeer2nd.utils.Direction;

public class King extends Piece {

    private King(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static King createWhiteKing(Position position) {
        return new King(Color.WHITE, Type.KING, position);
    }

    public static King createBlackKing(Position position) {
        return new King(Color.BLACK, Type.KING, position);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {
        int xDist = targetPosition.getFile() - sourcePosition.getFile();
        int yDist = targetPosition.getRank() - sourcePosition.getRank();

        boolean canMove = false;

        for (Direction direction : Direction.everyDirection()) {
            if (direction.getXDegree() == xDist && direction.getYDegree() == yDist) {
                canMove = true;
                break;
            }
        }

        if (!canMove) {
            throw new InvalidMovementException("킹은 한 칸만 이동할 수 있습니다.");
        }

    }


}
