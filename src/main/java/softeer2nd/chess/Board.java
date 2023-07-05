package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board extends ArrayList<Rank> {


    public Rank whitePawnList;
    public Rank blackPawnList;
    public Rank whitePieceList;
    public Rank blackPieceList;
    public Rank blankList;

    public Board() {
    }

    public void initialize() {

        whitePawnList = new Rank();
        blackPawnList = new Rank();

        whitePieceList = new Rank();
        blackPieceList = new Rank();

        blankList = new Rank();

        for (int i = 0; i < 8; i++) {
            whitePawnList.add(Piece.createPiece(Color.WHITE, Type.PAWN));
            blackPawnList.add(Piece.createPiece(Color.BLACK, Type.PAWN));
            blankList.add(Piece.createBlank());
        }

        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                this.add(new Rank());
                get(0).add(Piece.createPiece(Color.BLACK, Type.ROOK));
                get(0).add(Piece.createPiece(Color.BLACK, Type.KNIGHT));
                get(0).add(Piece.createPiece(Color.BLACK, Type.BISHOP));
                get(0).add(Piece.createPiece(Color.BLACK, Type.QUEEN));
                get(0).add(Piece.createPiece(Color.BLACK, Type.KING));
                get(0).add(Piece.createPiece(Color.BLACK, Type.BISHOP));
                get(0).add(Piece.createPiece(Color.BLACK, Type.KNIGHT));
                get(0).add(Piece.createPiece(Color.BLACK, Type.ROOK));
                continue;
            }

            if (i == 1) {
                this.add(blackPawnList);
                continue;
            }

            if (i == 6) {
                this.add(whitePawnList);
                continue;
            }

            if (i == 7) {
                this.add(new Rank());
                get(7).add(Piece.createPiece(Color.WHITE, Type.ROOK));
                get(7).add(Piece.createPiece(Color.WHITE, Type.KNIGHT));
                get(7).add(Piece.createPiece(Color.WHITE, Type.BISHOP));
                get(7).add(Piece.createPiece(Color.WHITE, Type.QUEEN));
                get(7).add(Piece.createPiece(Color.WHITE, Type.KING));
                get(7).add(Piece.createPiece(Color.WHITE, Type.BISHOP));
                get(7).add(Piece.createPiece(Color.WHITE, Type.KNIGHT));
                get(7).add(Piece.createPiece(Color.WHITE, Type.ROOK));
                continue;
            }

            else {
                this.add(blankList);
            }

        }

    }

    public int pieceCount() {

        return (int) this.stream()
                .flatMap(pieces -> pieces.stream())
                .count();
    }

    public String getWhitePawnsResult() {

        return representationPawnList(whitePawnList, Color.WHITE);
    }

    public String getBlackPawnsResult() {

        return representationPawnList(blackPawnList, Color.BLACK);
    }

    private String representationPawnList(List<Piece> pawnList, Color color) {

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
                        .map(piece -> String.valueOf(piece.getRepresentation()))
                        .collect(Collectors.joining())
                )
                .collect(Collectors.joining(StringUtils.NEWLINE)) + StringUtils.NEWLINE;
    }
}

