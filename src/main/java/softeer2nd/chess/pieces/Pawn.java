package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

public class Pawn extends Piece {
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

    }


}
