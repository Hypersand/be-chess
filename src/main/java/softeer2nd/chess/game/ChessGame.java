package softeer2nd.chess.game;

import softeer2nd.chess.Board;
import softeer2nd.chess.Position;
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

    public void move(String sourcePos, String targetPos) {

        Piece sourcePiece = board.findPiece(sourcePos);
        Piece targetPiece = board.findPiece(targetPos);

        Position sourcePosition = new Position(sourcePos);
        Position targetPosition = new Position(targetPos);
        
        sourcePiece.verifyMovePosition(sourcePosition, targetPosition);
        sourcePiece.verifyTargetPosition(targetPosition, sourcePiece.getColor(), targetPiece.getColor());

        board.get(targetPosition.getRank()).set(targetPosition.getFile(), sourcePiece);
        sourcePiece.movePosition(targetPos);
        board.get(sourcePosition.getRank()).set(sourcePosition.getFile(), Piece.createPiece(Color.NOCOLOR, Piece.Type.NO_PIECE, new Position(sourcePos)));
    }

}
