package softeer2nd.chess.view;

import softeer2nd.chess.Board;

public class ChessView {

    public void print(Board board) {
        System.out.println(board.toString());
    }

    public String showBoard(Board board) {
        return board.toString();
    }
}
