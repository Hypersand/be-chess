package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

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

    }
}
