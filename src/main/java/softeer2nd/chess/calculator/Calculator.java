package softeer2nd.chess.calculator;

import softeer2nd.chess.Board;
import softeer2nd.chess.Rank;
import softeer2nd.chess.pieces.Piece;

import java.util.List;

public class Calculator {

    private final Board board;

    public Calculator(Board board) {
        this.board = board;
    }

    public int pieceCount() {

        return board.stream()
                .mapToInt(Rank::pieceCount)
                .sum();
    }

    public int pieceCount(Piece.Color color, Piece.Type type) {

        return board.stream()
                .mapToInt(rank -> rank.pieceCount(color, type))
                .sum();
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
}
