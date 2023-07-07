package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private final List<Rank> board;

    public Rank whitePawnRank;
    public Rank blackPawnRank;

    public Board(List<Rank> board) {
        this.board = board;
    }

    public Stream<Rank> stream() {
        return board.stream();
    }

    public Rank get(int index) {
        return board.get(index);
    }

    public void initialize() {

        whitePawnRank = Rank.createWhitePawnRank(new ArrayList<>());
        blackPawnRank = Rank.createBlackPawnRank(new ArrayList<>());

        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                board.add(createPieceRank(Color.BLACK));
                continue;
            }

            if (i == 1) {
                board.add(blackPawnRank);
                continue;
            }

            if (i == 6) {
                board.add(whitePawnRank);
                continue;
            }

            if (i == 7) {
                board.add(createPieceRank(Color.WHITE));
            } else {
                Rank blankRank = new Rank(new ArrayList<>());
                for (int j = 0; j < 8; j++) {
                    String position = changePosToString(j, i);
                    blankRank.add(Piece.createBlank(new Position(position)));
                }
                board.add(blankRank);
            }
        }
    }

    private String changePosToString(int x, int y) {

        char pos_x = (char) ('a' + x);
        int pos_y = 8 - y;

        return String.valueOf(pos_x) + pos_y;
    }

    private Rank createPieceRank(Color color) {

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

    public String getWhitePawnsResult() {

        return representationPawnRank(whitePawnRank, Color.WHITE);
    }

    public String getBlackPawnsResult() {

        return representationPawnRank(blackPawnRank, Color.BLACK);
    }

    private String representationPawnRank(Rank pawnRank, Color color) {

        return pawnRank.representationPawnRank(color);
    }

    public Piece findPiece(String pos) {

        String[] px_py = pos.split("");

        int pos_x = px_py[0].charAt(0) - 'a';
        int pos_y = 8 - Integer.parseInt(px_py[1]);

        return board.get(pos_y).get(pos_x);
    }

    @Override
    public String toString() {

        return board.stream()
                .map(rank -> rank.isEmpty() ? "........" : rank.representationRank())
                .collect(Collectors.joining(StringUtils.NEWLINE)) + StringUtils.NEWLINE;
    }

    public void initializeEmpty() {

        board.clear();

        for (int i = 0; i < 8; i++) {
            Rank blankRank = new Rank(new ArrayList<>());
            for (int j = 0; j < 8; j++) {
                String position = changePosToString(j, i);
                blankRank.add(Piece.createBlank(new Position(position)));
            }
            board.add(blankRank);
        }
    }

    public List<Piece> getPieceListByColor(Color color) {

        List<Piece> pieceList = board.stream()
                .flatMap(rank -> rank.getPieces().stream())
                .filter(piece -> piece.getColor().equals(color))
                .collect(Collectors.toList());

        sortByPointDesc(pieceList);

        return pieceList;
    }

    public void sortByPointDesc(List<Piece> pieceList) {
        pieceList.sort(Comparator.comparingDouble((Piece p) -> p.getType().getDefaultPoint()).reversed());
    }
}

