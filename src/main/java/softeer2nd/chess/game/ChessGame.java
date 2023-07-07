package softeer2nd.chess.game;

import softeer2nd.chess.Board;
import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;
import softeer2nd.chess.exception.InvalidPositionException;
import softeer2nd.chess.exception.InvalidSameColorException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;

import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void move(String position, Piece piece) {
        Map<String, Integer> posMap = getPosition(position);

        board.get(posMap.get("y")).set(posMap.get("x"), piece);
    }

    private Map<String, Integer> getPosition(String position) {
        String[] positions = position.split("");
        int pos_x = positions[0].charAt(0) - 'a';
        int pos_y = 8 - Integer.parseInt(positions[1]);

        Map<String, Integer> posMap = new HashMap<>();
        posMap.put("x", pos_x);
        posMap.put("y", pos_y);

        return posMap;
    }

    public void move(String sourcePosition, String targetPosition) {

        Map<String, Integer> sourcePosMap = getPosition(sourcePosition);
        Map<String, Integer> targetPosMap = getPosition(targetPosition);

        Piece piece = board.findPiece(sourcePosition);
        board.get(targetPosMap.get("y")).set(targetPosMap.get("x"), board.findPiece(sourcePosition));
        piece.movePosition(targetPosition);
        board.get(sourcePosMap.get("y")).set(sourcePosMap.get("x"), Piece.createBlank(new Position(sourcePosition)));
    }

    public void kingMove(String sourcePosition, String targetPosition) {

        Piece king = board.findPiece(sourcePosition);
        Map<String, Integer> sourcePosMap = getPosition(sourcePosition);
        Map<String, Integer> targetPosMap = getPosition(targetPosition);

        validateKingMovement(sourcePosMap, targetPosMap);
        validateTargetPosition(targetPosMap, king.getColor());

        board.get(targetPosMap.get("y")).set(targetPosMap.get("x"), board.findPiece(sourcePosition));
        king.movePosition(targetPosition);
        board.get(sourcePosMap.get("y")).set(sourcePosMap.get("x"), Piece.createBlank(new Position(sourcePosition)));
    }

    private void validateKingMovement(Map<String, Integer> sourcePosMap, Map<String, Integer> targetPosMap) {

        if (Math.abs(sourcePosMap.get("x") - targetPosMap.get("x")) <= 1
                && Math.abs(sourcePosMap.get("y") - targetPosMap.get("y")) <= 1) {
            return;
        }
        throw new InvalidMovementException("킹은 한 칸만 이동할 수 있습니다.");

    }

    private void validateTargetPosition(Map<String, Integer> targetPosMap, Color color) {

        if (targetPosMap.get("x") < 0 || targetPosMap.get("x") > 7) {
            throw new InvalidPositionException("체스판 위에 말을 배치해 주세요!");
        }

        if (targetPosMap.get("y") < 0 || targetPosMap.get("y") > 7) {
            throw new InvalidPositionException("체스판 위에 말을 배치해 주세요!");
        }

        Piece targetPiece = board.get(targetPosMap.get("y")).get(targetPosMap.get("x"));

        if (targetPiece.getColor().equals(color)) {
            throw new InvalidSameColorException("같은 색 말 위로 이동할 수 없습니다!");
        }
    }

    private void queenMove(String sourcePosition, String targetPosition) {


    }

    private void validateQueenMovement() {

    }

}
