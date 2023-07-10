package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;

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
        if (Math.abs(sourcePosition.getRank() - targetPosition.getRank()) <= 1
                && Math.abs(sourcePosition.getFile() - targetPosition.getFile()) <= 1) {
            return;
        }
        throw new InvalidMovementException("킹은 한 칸만 이동할 수 있습니다.");
    }


}
