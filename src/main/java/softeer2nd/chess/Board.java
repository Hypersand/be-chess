package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board extends ArrayList<ArrayList<Piece>> {


    public List<Piece> whitePawnList;
    public List<Piece> blackPawnList;
    public List<Piece> whitePieceList;
    public List<Piece> blackPieceList;

    public Board() {
    }

    public Piece findPawn(int row, int col) {
        return this.get(row).get(col);
    }

    public void initialize() {

        whitePawnList = new ArrayList<>();
        blackPawnList = new ArrayList<>();

        whitePieceList = new ArrayList<>();
        blackPieceList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            whitePawnList.add(Piece.createWhitePawn());
            blackPawnList.add(Piece.createBlackPawn());
        }

        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                this.add(new ArrayList<>());
                get(0).add(Piece.createBlackRook());
                get(0).add(Piece.createBlackKnight());
                get(0).add(Piece.createBlackBishop());
                get(0).add(Piece.createBlackQueen());
                get(0).add(Piece.createBlackKing());
                get(0).add(Piece.createBlackBishop());
                get(0).add(Piece.createBlackKnight());
                get(0).add(Piece.createBlackRook());
                continue;
            }

            if (i == 1) {
                this.add((ArrayList<Piece>) blackPawnList);
                continue;
            }

            if (i == 6) {
                this.add((ArrayList<Piece>) whitePawnList);
                continue;
            }

            if (i == 7) {
                this.add(new ArrayList<>());
                get(7).add(Piece.createWhiteRook());
                get(7).add(Piece.createWhiteKnight());
                get(7).add(Piece.createWhiteBishop());
                get(7).add(Piece.createWhiteQueen());
                get(7).add(Piece.createWhiteKing());
                get(7).add(Piece.createWhiteBishop());
                get(7).add(Piece.createWhiteKnight());
                get(7).add(Piece.createWhiteRook());
                continue;
            }

            this.add(new ArrayList<>());
        }

    }

    public int pieceCount() {
        int count = 0;
        for (ArrayList<Piece> pieces : this) {
            for (Piece piece : pieces) {
                count++;
            }
        }
        return count;
    }

    public String getWhitePawnsResult() {

        return representationPawnList(whitePawnList, Piece.WHITE_COLOR);
    }

    public String getBlackPawnsResult() {

        return representationPawnList(blackPawnList, Piece.BLACK_COLOR);
    }

    private String representationPawnList(List<Piece> pawnList, String color) {

        StringBuilder sb = new StringBuilder();

        return pawnList.stream()
                .filter(pawn -> pawn.getColor().equals(color))
                .map(pawn -> String.valueOf(pawn.getRepresentation()))
                .collect(Collectors.joining());
    }

    public void print() {
        System.out.println(this);
    }

    public String showBoard() {
        return this.toString();
    }

    @Override
    public String toString() {

        return this.stream()
                .map(col -> col.isEmpty() ? "........" : col.stream()
                        .map(pawn -> String.valueOf(pawn.getRepresentation()))
                        .collect(Collectors.joining())
                )
                .collect(Collectors.joining(StringUtils.NEWLINE)) + StringUtils.NEWLINE;
    }
}

