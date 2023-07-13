package softeer2nd.chess.game;

import softeer2nd.chess.pieces.Piece.Color;

public class Turn {

    private Color color;
    
    public Turn() {
        color = Color.WHITE;
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public void changeTurn() {
        if (isWhite()) {
            color = Color.BLACK;
            return;
        }

        color = Color.WHITE;
    }

}
