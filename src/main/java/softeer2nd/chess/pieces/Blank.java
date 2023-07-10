package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exception.BlankPieceException;

public class Blank extends Piece{

    private Blank(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static Blank createBlank(Position position) {
        return new Blank(Color.NOCOLOR, Type.NO_PIECE, position);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {
        throw new BlankPieceException("말이 존재하지 않는 위치입니다.");
    }
}
