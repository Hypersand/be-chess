package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;

public class Queen extends Piece {

    private Queen(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static Queen createWhiteQueen(Position position) {
        return new Queen(Color.WHITE, Type.QUEEN, position);
    }

    public static Queen createBlackQueen(Position position) {
        return new Queen(Color.BLACK, Type.QUEEN, position);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {
        int xDist = targetPosition.getFile() - sourcePosition.getFile();
        int yDist = targetPosition.getRank() - sourcePosition.getRank();

        if ((xDist == 0 || yDist == 0) || (Math.abs(xDist) == Math.abs(yDist))) {
            return;
        }

        throw new InvalidMovementException("퀸의 이동이 올바르지 않습니다!");
    }
}
