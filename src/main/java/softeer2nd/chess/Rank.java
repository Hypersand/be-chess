package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Rank {

    //Rank의 조건
    //하나의 Rank에 8개의 값 밖에 못 들어 간다.
    private final List<Piece> rank;

    public Rank(List<Piece> rank) {
        this.rank = rank;
    }

    public void add(Piece piece) {
        rank.add(piece);
    }

    public Piece get(int pos) {
        return rank.get(pos);
    }

    public void set(int index, Piece piece) {
        rank.set(index, piece);
    }

    public int pieceCount() {
        return (int) rank.stream().count();
    }

    public int pieceCount(Color color, Type type) {
        return (int) rank.stream()
                .filter(piece -> piece.getColor().equals(color) && piece.getType().equals(type))
                .count();
    }

    public String representationPawnRank(Color color) {
        return rank.stream()
                .filter(piece -> piece.getColor().equals(color))
                .map(piece -> String.valueOf(piece.getRepresentation()))
                .collect(Collectors.joining());
    }

    public String representationRank() {
        return rank.stream()
                .map(piece -> String.valueOf(piece.getRepresentation()))
                .collect(Collectors.joining());
    }

    public boolean isEmpty() {
        return rank.size() == 0;
    }

    public static Rank createWhitePawnRank(List<Piece> whitePawns) {

        Rank whitePawnRank = new Rank(whitePawns);

        for (int i = 0; i < 8; i++) {
            whitePawnRank.add(Piece.createPiece(Color.WHITE, Type.PAWN));
        }

        return whitePawnRank;
    }

    public static Rank createBlackPawnRank(List<Piece> blackPawns) {

        Rank blackPawnRank = new Rank(blackPawns);

        for (int i = 0; i < 8; i++) {
            blackPawnRank.add(Piece.createPiece(Color.BLACK, Type.PAWN));
        }

        return blackPawnRank;
    }

    public double calculatePoint(Color color) {
        return rank.stream()
                .filter(piece -> piece.getColor().equals(color) && !piece.getType().equals(Type.PAWN))
                .mapToDouble(piece -> piece.getType().getDefaultPoint())
                .sum();
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(rank);
    }

}
