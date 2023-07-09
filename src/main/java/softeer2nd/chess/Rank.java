package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Rank {

    public static final int WHITE_PAWN_RANK = 2;
    public static final int BLACK_PAWN_RANK = 7;
    public static final int RANK_MAX_LENGTH = 8;

    private final List<Piece> rank;

    public Rank(List<Piece> rank) {
        this.rank = rank;
    }

    public void add(Piece piece) {
        rank.add(piece);
    }

    public Piece get(int index) {
        return rank.get(index);
    }

    public void set(int index, Piece piece) {
        rank.set(index, piece);
    }

    public int pieceCount() {
        return rank.size();
    }

    public boolean isEmpty() {
        return rank.size() == 0;
    }

    public int pieceCount(Color color, Type type) {
        return (int) rank.stream()
                .filter(piece -> isPieceColorEquals(color, piece) && isPieceTypeEquals(type, piece))
                .count();
    }

    public String representationPawnRank(Color color) {
        return rank.stream()
                .filter(piece -> isPieceColorEquals(color, piece))
                .map(piece -> String.valueOf(piece.getRepresentation()))
                .collect(Collectors.joining());
    }

    public String representationRank() {
        return rank.stream()
                .map(piece -> String.valueOf(piece.getRepresentation()))
                .collect(Collectors.joining());
    }

    public static Rank createPieceRank(Color color) {

        List<Piece> pieces;

        if (color.equals(Color.WHITE)) {
            pieces = Arrays.asList(
                    Piece.createPiece(color, Type.ROOK, new Position("a1")),
                    Piece.createPiece(color, Type.KNIGHT, new Position("b1")),
                    Piece.createPiece(color, Type.BISHOP, new Position("c1")),
                    Piece.createPiece(color, Type.QUEEN, new Position("d1")),
                    Piece.createPiece(color, Type.KING, new Position("e1")),
                    Piece.createPiece(color, Type.BISHOP, new Position("f1")),
                    Piece.createPiece(color, Type.KNIGHT, new Position("g1")),
                    Piece.createPiece(color, Type.ROOK, new Position("h1"))
            );
        }

        else {
            pieces = Arrays.asList(
                    Piece.createPiece(color, Type.ROOK, new Position("a8")),
                    Piece.createPiece(color, Type.KNIGHT, new Position("b8")),
                    Piece.createPiece(color, Type.BISHOP, new Position("c8")),
                    Piece.createPiece(color, Type.QUEEN, new Position("d8")),
                    Piece.createPiece(color, Type.KING, new Position("e8")),
                    Piece.createPiece(color, Type.BISHOP, new Position("f8")),
                    Piece.createPiece(color, Type.KNIGHT, new Position("g8")),
                    Piece.createPiece(color, Type.ROOK, new Position("h8"))
            );
        }

        return new Rank(pieces);
    }

    public static Rank createWhitePawnRank(List<Piece> whitePawns) {

        Rank whitePawnRank = new Rank(whitePawns);

        for (int i = 0; i < RANK_MAX_LENGTH; i++) {
            String position = String.valueOf((char) ('a' + i)) + WHITE_PAWN_RANK;
            whitePawnRank.add(Piece.createPiece(Color.WHITE, Type.PAWN, new Position(position)));
        }

        return whitePawnRank;
    }

    public static Rank createBlackPawnRank(List<Piece> blackPawns) {

        Rank blackPawnRank = new Rank(blackPawns);

        for (int i = 0; i < RANK_MAX_LENGTH; i++) {
            String position = String.valueOf((char) ('a' + i)) + BLACK_PAWN_RANK;
            blackPawnRank.add(Piece.createPiece(Color.BLACK, Type.PAWN, new Position(position)));
        }

        return blackPawnRank;
    }

    public double calculatePoint(Color color) {
        return rank.stream()
                .filter(piece -> isPieceColorEquals(color, piece) && !isPieceTypeEquals(Type.PAWN, piece))
                .mapToDouble(Piece::getDefaultPoint)
                .sum();
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(rank);
    }

    private boolean isPieceColorEquals(Color color, Piece piece) {
        return piece.getColor().equals(color);
    }

    private boolean isPieceTypeEquals(Type type, Piece piece) {
        return piece.getType().equals(type);
    }

}
