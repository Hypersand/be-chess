package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import java.util.Objects;

public class Piece {
    private final Color color;
    private final Type type;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public char getRepresentation() {
        return type.getRepresentation(getColor());
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public static Piece createPiece(Color color, Type type) {
        return new Piece(color, type);
    }

    public static Piece createPiece(Color color, Type type, Position position) {
        return new Piece(color, type);
    }

    public static Piece createBlank() {
        return new Piece(Color.NOCOLOR, Type.NO_PIECE);
    }

    public static Piece createBlank(Position position) {
//        String[] px_py = position.getPosition().split("");
//        int pos_x = px_py[0].charAt(0) - 'a';
//        int pos_y = 8 - Integer.parseInt(px_py[1]);
        return new Piece(Color.NOCOLOR, Type.NO_PIECE);
    }

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
        private Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public double getDefaultPoint() {
            return defaultPoint;
        }

        public char getRepresentation(Color color) {
            if (color.equals(Color.WHITE)) {
                return representation;
            }

            return Character.toUpperCase(representation);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
