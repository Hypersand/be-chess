package softeer2nd.chess.game;

import softeer2nd.chess.pieces.Piece.Color;

public class Turn {

    private Color color;
    private boolean isFirstTurn;

    public Turn() {
        color = Color.WHITE;
        isFirstTurn = true;
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isFirstTurn() {
        return isFirstTurn;
    }

    public void finishFirstTurn() {
        isFirstTurn = false;
    }

    public void changeTurn() {
        if (isWhite()) {
            color = Color.BLACK;
            return;
        }

        color = Color.WHITE;
    }

}
