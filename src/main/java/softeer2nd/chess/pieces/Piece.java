package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidPositionException;
import softeer2nd.chess.exception.InvalidSameColorException;

import java.util.Objects;

public abstract class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0),
        NO_PIECE('.', 0.0);

        private final char representation;
        private final double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }
    }
    private final Color color;
    private final Type type;

    private final Position position;

    protected Piece(Color color, Type type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public char getRepresentation() {
        if (color.equals(Color.WHITE)) {
            return type.representation;
        }

        return Character.toUpperCase(type.representation);
    }

    public double getDefaultPoint() {
        return type.defaultPoint;
    }

    public void movePosition(String position) {
        this.position.movePosition(position);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlank() {
        return type.equals(Type.NO_PIECE);
    }

    public boolean isSameType(Type type) {
        return this.type == type;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public static Piece createPiece(Color color, Type type, Position position) {

        if (type.equals(Type.PAWN)) {
            return color.equals(Color.WHITE) ? Pawn.createWhitePawn(position) : Pawn.createBlackPawn(position);
        }

        if (type.equals(Type.KING)) {
            return color.equals(Color.WHITE) ? King.createWhiteKing(position) : King.createBlackKing(position);
        }

        if (type.equals(Type.QUEEN)) {
            return color.equals(Color.WHITE) ? Queen.createWhiteQueen(position) : Queen.createBlackQueen(position);
        }

        if (type.equals(Type.ROOK)) {
            return color.equals(Color.WHITE) ? Rook.createWhiteRook(position) : Rook.createBlackRook(position);
        }

        if (type.equals(Type.BISHOP)) {
            return color.equals(Color.WHITE) ? Bishop.createWhiteBishop(position) : Bishop.createBlackBishop(position);
        }

        if (type.equals(Type.KNIGHT)) {
            return color.equals(Color.WHITE) ? Knight.createWhiteKnight(position) : Knight.createBlackKnight(position);
        }

        return Blank.createBlank(position);
    }

    public abstract void verifyMovePosition(Position sourcePosition, Position targetPosition);

    //위치 수정 필요
    public void verifyTargetPosition(Position targetPosition) {

        if (targetPosition.getRank() < 0 || targetPosition.getRank() > 7) {
            throw new InvalidPositionException("체스판 위에 말을 배치해 주세요!");
        }

        if (targetPosition.getFile() < 0 || targetPosition.getFile() > 7) {
            throw new InvalidPositionException("체스판 위에 말을 배치해 주세요!");
        }
    }

    public void verifyTargetColor(Color sourceColor, Color targetColor) {
        if (targetColor.equals(sourceColor)) {
            throw new InvalidSameColorException("같은 색 말 위로 이동할 수 없습니다!");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type && position == piece.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
