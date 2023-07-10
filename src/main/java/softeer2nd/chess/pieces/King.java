package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

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

    }


}
