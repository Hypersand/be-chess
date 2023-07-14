package softeer2nd.chess.game;

import softeer2nd.chess.Board;
import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;
import softeer2nd.chess.exception.InvalidTurnException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.utils.Direction;

import java.util.stream.IntStream;

import static softeer2nd.chess.exception.InvalidMovementException.*;
import static softeer2nd.chess.exception.InvalidTurnException.*;

public class ChessGame {

    private static final int RANK_MAX_LENGTH = 8;
    private static final int FILE_MAX_LENGTH = 8;

    private final Board board;
    private final Turn turn;

    public ChessGame(Board board, Turn turn) {
        this.board = board;
        this.turn = turn;
    }

    public double calculatePoint(Piece.Color color) {
        return board.stream()
                .mapToDouble(rank -> rank.calculatePoint(color))
                .sum() + calculatePawn(color);
    }

    private double calculatePawn(Piece.Color color) {
        double result = 0;
        int pawnCount;

        for (int i = 0; i < RANK_MAX_LENGTH; i++) {
            pawnCount = getPawnCount(color, i);
            if (pawnCount == 1) {
                result += 1;
                continue;
            }
            if (pawnCount > 1) {
                result += (pawnCount * 0.5);
            }
        }

        return result;
    }

    private int getPawnCount(Color color, int rankLine) {
        return (int) IntStream.range(0, FILE_MAX_LENGTH)
                .mapToObj(board::get)
                .map(rank -> rank.get(rankLine))
                .filter(piece -> piece.isSameColor(color) && piece.isSameType(Piece.Type.PAWN))
                .count();
    }

    public void move(String pos, Piece piece) {
        Position position = new Position(pos);
        board.get(position.getRank()).set(position.getFile(), piece);
    }

    public void move(String sourcePos, String targetPos) {
        Position sourcePosition = new Position(sourcePos);
        Position targetPosition = new Position(targetPos);

        Piece sourcePiece = board.findPiece(sourcePos);
        Piece targetPiece = board.findPiece(targetPos);

        verifyTurn(sourcePiece);
        changeTurn();

        targetPosition.verifyTargetPosition();
        sourcePiece.verifyMovePosition(sourcePosition, targetPosition);

        if (sourcePiece.isSameType(Piece.Type.PAWN)) {
            verifyPawnMove(sourcePosition, targetPosition, targetPiece);
        }
        if (!sourcePiece.isSameType(Piece.Type.KNIGHT)) {
            verifyPathObstructed(sourcePosition, targetPosition);
        }

        targetPiece.verifyTargetColor(sourcePiece.getColor());

        board.get(targetPosition.getRank()).set(targetPosition.getFile(), sourcePiece);
        sourcePiece.movePosition(targetPos);
        board.get(sourcePosition.getRank()).set(sourcePosition.getFile(), Piece.createPiece(Color.NOCOLOR, Piece.Type.NO_PIECE, new Position(sourcePos)));
    }

    private void verifyTurn(Piece sourcePiece) {
        if (turn.isWhite() && !sourcePiece.isWhite()) {
            throw new InvalidTurnException(WHITE_TURN_MESSAGE);
        }
        if (!turn.isWhite() && !sourcePiece.isBlack()) {
            throw new InvalidTurnException(BLACK_TURN_MESSAGE);
        }
    }

    private void changeTurn() {
        turn.changeTurn();
    }

    private void verifyPathObstructed(Position sourcePosition, Position targetPosition) {
        Direction direction = Direction.getDirection(sourcePosition, targetPosition);

        int verifyFile = sourcePosition.getFile();
        int verifyRank = sourcePosition.getRank();

        while (true) {
            verifyFile += direction.getXDegree();
            verifyRank += direction.getYDegree();

            if (verifyRank == targetPosition.getRank() && verifyFile == targetPosition.getFile()) {
                break;
            }
            if (!board.findPiece(verifyRank, verifyFile).isBlank()) {
                throw new InvalidMovementException(PATH_OBSTRUCTED_MESSAGE);
            }
        }
    }

    private void verifyPawnMove(Position sourcePosition, Position targetPosition, Piece targetPiece) {
        int xDist = targetPosition.getFile() - sourcePosition.getFile();

        if (targetPiece.isBlank() && Math.abs(xDist) > 0) {
            throw new InvalidMovementException(PAWN_MOVE_DIAGONAL_MESSAGE);
        }
        if (!targetPiece.isBlank() && xDist == 0) {
            throw new InvalidMovementException(PAWN_MOVE_STRAIGHT_MESSAGE);
        }
    }

}
