package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

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

    }
}
