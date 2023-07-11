package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;

public class Pawn extends Piece {

    private static final int RANK_WHITE_PAWN = 6;
    private static final int RANK_BLACK_PAWN = 1;

    private Pawn(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static Pawn createWhitePawn(Position position) {
        return new Pawn(Color.WHITE, Type.PAWN, position);
    }

    public static Pawn createBlackPawn(Position position) {
        return new Pawn(Color.BLACK, Type.PAWN, position);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {

        int xDist = targetPosition.getFile() - sourcePosition.getFile();
        int yDist = targetPosition.getRank() - sourcePosition.getRank();

        if (getColor().equals(Color.WHITE)) {
            if (sourcePosition.getRank() == RANK_WHITE_PAWN) {
                if (targetPosition.getFile() != sourcePosition.getFile()) {
                    throw new InvalidMovementException("폰은 다른 파일로 이동할 수 없습니다!");
                }

                if (Math.abs(yDist) > 2) {
                    throw new InvalidMovementException("시작 위치의 폰은 최대 두 칸 이동할 수 있습니다!");
                }
                return;
            }

            if (Math.abs(xDist) > 0 || yDist != -1) {
                throw new InvalidMovementException("폰의 이동이 올바르지 않습니다!");
            }

        }

        if (getColor().equals(Color.BLACK)) {
            if (sourcePosition.getRank() == RANK_BLACK_PAWN) {
                if (targetPosition.getFile() != sourcePosition.getFile()) {
                    throw new InvalidMovementException("폰의 이동이 올바르지 않습니다!");
                }

                if (Math.abs(yDist) > 2) {
                    throw new InvalidMovementException("폰의 이동이 올바르지 않습니다!");
                }
                return;
            }

            if (Math.abs(xDist) > 0 || yDist != 1) {
                throw new InvalidMovementException("폰의 이동이 올바르지 않습니다!");
            }
        }
    }


}
