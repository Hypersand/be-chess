package softeer2nd.chess.game;

import softeer2nd.chess.Board;
import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;
import softeer2nd.chess.exception.InvalidPositionException;
import softeer2nd.chess.exception.InvalidSameColorException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void move(String pos, Piece piece) {
        Position position = new Position(pos);
        board.get(position.getRank()).set(position.getFile(), piece);
    }

    public double calculatePoint(Piece.Color color) {
        return board.stream()
                .mapToDouble(rank -> rank.calculatePoint(color))
                .sum() + calculatePawn(color);
    }

    private double calculatePawn(Piece.Color color) {

        double result = 0;

        for (int i = 0; i < 8; i++) {
            int pawnCount = 0;

            for (int j = 0; j < 8; j++) {
                Piece piece = board.get(j).get(i);

                if (piece.getType().equals(Piece.Type.PAWN) && piece.getColor().equals(color)) {
                    pawnCount++;
                }
            }

            if (pawnCount == 1) {
                result += 1;
            }

            if (pawnCount > 1) {
                result += (pawnCount * 0.5);
            }
        }

        return result;
    }

    public void move(String sourcePos, String targetPos) {

        Position sourcePosition = new Position(sourcePos);
        Position targetPosition = new Position(targetPos);

        Piece piece = board.findPiece(sourcePos);
        board.get(targetPosition.getRank()).set(targetPosition.getFile(), piece);
        piece.movePosition(targetPos);
        board.get(sourcePosition.getRank()).set(sourcePosition.getFile(), Piece.createBlank(new Position(sourcePos)));
    }

    public void kingMove(String sourcePos, String targetPos) {

        Piece king = board.findPiece(sourcePos);

        Position sourcePosition = new Position(sourcePos);
        Position targetPosition = new Position(targetPos);
        
        validateKingMovement(sourcePosition, targetPosition);
        validateTargetPosition(targetPosition, king.getColor());

        board.get(targetPosition.getRank()).set(targetPosition.getFile(), king);
        king.movePosition(targetPos);
        board.get(sourcePosition.getRank()).set(sourcePosition.getFile(), Piece.createBlank(new Position(sourcePos)));
    }

    private void validateKingMovement(Position sourcePosition, Position targetPosition) {

        if (Math.abs(sourcePosition.getRank() - targetPosition.getRank()) <= 1
                && Math.abs(sourcePosition.getFile() - targetPosition.getFile()) <= 1) {
            return;
        }
        throw new InvalidMovementException("킹은 한 칸만 이동할 수 있습니다.");

    }

    private void validateTargetPosition(Position targetPosition, Color color) {

        if (targetPosition.getRank() < 0 || targetPosition.getRank() > 7) {
            throw new InvalidPositionException("체스판 위에 말을 배치해 주세요!");
        }

        if (targetPosition.getFile() < 0 || targetPosition.getFile() > 7) {
            throw new InvalidPositionException("체스판 위에 말을 배치해 주세요!");
        }

        Piece targetPiece = board.get(targetPosition.getRank()).get(targetPosition.getFile());

        if (targetPiece.getColor().equals(color)) {
            throw new InvalidSameColorException("같은 색 말 위로 이동할 수 없습니다!");
        }
    }

    private void queenMove(String sourcePosition, String targetPosition) {


    }

    private void validateQueenMovement() {

    }

}
