package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

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

    }
}
