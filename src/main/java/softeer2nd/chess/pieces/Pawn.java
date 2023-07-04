package softeer2nd.chess.pieces;

public class Pawn {

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    private String color;

    public Pawn(String color) {
        this.color = color;
    }

    public Pawn() {
        this.color = "white";
    }

    public String getColor() {
        return this.color;
    }
}
