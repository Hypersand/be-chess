package softeer2nd.chess.game;

public class Turn {

    private boolean isWhiteTurn;
    private boolean isFirstTurn;

    public Turn() {
        isWhiteTurn = true;
        isFirstTurn = true;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public boolean isFirstTurn() {
        return isFirstTurn;
    }

    public void changeFirstTurn() {
        isFirstTurn = false;
    }

    public void changeTurn() {
        if (isWhiteTurn) {
            isWhiteTurn = false;
            return;
        }

        isWhiteTurn = true;
    }

}
