package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Rank {

    private static final int WHITE_PAWN_RANK = 2;
    private static final int BLACK_PAWN_RANK = 7;
    private static final int FILE_MAX_LENGTH = 8;

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

    public Stream<Piece> stream() {
        return rank.stream();
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

        for (int i = 0; i < FILE_MAX_LENGTH; i++) {
            whitePawnRank.add(Piece.createPiece(Color.WHITE, Type.PAWN, new Position(WHITE_PAWN_RANK, i)));
        }

        return whitePawnRank;
    }

    public static Rank createBlackPawnRank(List<Piece> blackPawns) {

        Rank blackPawnRank = new Rank(blackPawns);

        for (int i = 0; i < FILE_MAX_LENGTH; i++) {
            blackPawnRank.add(Piece.createPiece(Color.BLACK, Type.PAWN, new Position(BLACK_PAWN_RANK, i)));
        }

        return blackPawnRank;
    }

    public static Rank createBlankRank(int rankLine) {

        Rank blankRank = new Rank(new ArrayList<>());

        for (int j = 0; j < FILE_MAX_LENGTH; j++) {
            blankRank.add(Piece.createPiece(Color.NOCOLOR, Type.NO_PIECE, new Position(rankLine, j)));
        }

        return blankRank;
    }

    public double calculatePoint(Color color) {
        return rank.stream()
                .filter(piece -> isPieceColorEquals(color, piece) && !isPieceTypeEquals(Type.PAWN, piece))
                .mapToDouble(Piece::getDefaultPoint)
                .sum();
    }

    private boolean isPieceColorEquals(Color color, Piece piece) {
        return piece.getColor().equals(color);
    }

    private boolean isPieceTypeEquals(Type type, Piece piece) {
        return piece.getType().equals(type);
    }

}
