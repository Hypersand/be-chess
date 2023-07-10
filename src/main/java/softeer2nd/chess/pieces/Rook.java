package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;

public class Rook extends Piece{
    private Rook(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static Rook createWhiteRook(Position position) {
        return new Rook(Color.WHITE, Type.ROOK, position);
    }

    public static Rook createBlackRook(Position position) {
        return new Rook(Color.BLACK, Type.ROOK, position);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {
        int xDist = targetPosition.getFile() - sourcePosition.getFile();
        int yDist = targetPosition.getRank() - sourcePosition.getRank();

        if (xDist == 0 || yDist == 0) {
            return;
        }

        throw new InvalidMovementException("룩의 이동이 올바르지 않습니다!");
    }
}
