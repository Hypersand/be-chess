package softeer2nd.chess.game;

import softeer2nd.chess.Board;
import softeer2nd.chess.Position;
import softeer2nd.chess.pieces.Piece;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void move(String position, Piece piece) {
        String[] px_py = position.split("");

        int pos_x = px_py[0].charAt(0) - 'a';
        int pos_y = 8 - Integer.parseInt(px_py[1]);

        board.get(pos_y).set(pos_x, piece);
    }

    public void move(String sourcePosition, String targetPosition) {
        String[] px_py_source = sourcePosition.split("");
        String[] px_py_target = targetPosition.split("");

        int pos_x_source = px_py_source[0].charAt(0) - 'a';
        int pos_y_source = 8 - Integer.parseInt(px_py_source[1]);

        int pos_x_target = px_py_target[0].charAt(0) - 'a';
        int pos_y_target = 8 - Integer.parseInt(px_py_target[1]);

        Piece piece = board.findPiece(sourcePosition);
        board.get(pos_y_target).set(pos_x_target, board.findPiece(sourcePosition));
        piece.movePosition(targetPosition);
        board.get(pos_y_source).set(pos_x_source, Piece.createBlank(new Position(sourcePosition)));
    }

}
