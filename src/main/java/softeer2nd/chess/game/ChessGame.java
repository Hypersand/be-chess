package softeer2nd.chess.game;

import softeer2nd.chess.Board;
import softeer2nd.chess.Position;
import softeer2nd.chess.exception.InvalidMovementException;
import softeer2nd.chess.exception.InvalidPositionException;
import softeer2nd.chess.exception.InvalidTurnException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.utils.Direction;

import java.util.stream.IntStream;

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
            throw new InvalidTurnException("흰색이 두어야 할 차례입니다!");
        }
        if (!turn.isWhite() && !sourcePiece.isBlack()) {
            throw new InvalidTurnException("검은색이 두어야 할 차례입니다!");
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
                throw new InvalidMovementException("배치하려는 위치의 경로 중간에 말이 있으면 이동할 수 없습니다!");
            }
        }
    }

    private void verifyPawnMove(Position sourcePosition, Position targetPosition, Piece targetPiece) {
        int xDist = targetPosition.getFile() - sourcePosition.getFile();

        if (targetPiece.isBlank() && Math.abs(xDist) > 0) {
            throw new InvalidMovementException("폰은 기물이 없는 곳으로 대각선 이동할 수 없습니다!");
        }
        if (!targetPiece.isBlank() && xDist == 0) {
            throw new InvalidMovementException("폰은 기물이 있는 곳으로 직선 이동할 수 없습니다!");
        }
    }

}
