package softeer2nd.chess.game;

import softeer2nd.chess.Board;
import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
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

    public void move(String pos, Piece piece) {
        Position position = new Position(pos);

        board.get(position.getRank()).set(position.getFile(), piece);
    }

    public void move(String sourcePos, String targetPos) {

        Position sourcePosition = new Position(sourcePos);
        Position targetPosition = new Position(targetPos);

        Piece sourcePiece = board.findPiece(sourcePos);

        sourcePiece.verifyMovePosition(sourcePosition, targetPosition);
        sourcePiece.verifyTargetPosition(targetPosition);

        Piece targetPiece = board.findPiece(targetPos);

        if (sourcePiece.getType().equals(Piece.Type.PAWN)) {
            verifyPawnMove(sourcePosition, targetPosition, targetPiece);
        }

        verifyPathObstructed();

        targetPiece.verifyTargetColor(sourcePiece.getColor(), targetPiece.getColor());

        board.get(targetPosition.getRank()).set(targetPosition.getFile(), sourcePiece);
        sourcePiece.movePosition(targetPos);
        board.get(sourcePosition.getRank()).set(sourcePosition.getFile(), Piece.createPiece(Color.NOCOLOR, Piece.Type.NO_PIECE, new Position(sourcePos)));
    }

    //1. 목적지까지의 경로 중간에 말이 있을 경우
    private void verifyPathObstructed() {

    }

    private void verifyPawnMove(Position sourcePosition, Position targetPosition, Piece targetPiece) {
        int xDist = targetPosition.getFile() - sourcePosition.getFile();

        if (targetPiece.isBlank() && Math.abs(xDist) > 0) {
            throw new InvalidMovementException("폰은 기물이 없는 곳으로 대각선 이동할 수 없습니다!");
        }

        if (xDist == 0) {
            throw new InvalidMovementException("폰은 기물이 있는 곳으로 직선 이동할 수 없습니다!");
        }
    }

}
