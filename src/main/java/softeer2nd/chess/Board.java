package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.utils.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    public static final int RANK_MAX_LENGTH = 8;
    public static final int FILE_MAX_LENGTH = 8;
    public static final int RANK_WHITE_PAWN = 6;
    public static final int RANK_WHITE_PIECE = 7;
    public static final int RANK_BLACK_PAWN = 1;
    public static final int RANK_BLACK_PIECE = 0;

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

        for (int i = 0; i < FILE_MAX_LENGTH; i++) {
            if (i == RANK_BLACK_PIECE) {
                board.add(Rank.createPieceRank(Color.BLACK));
                continue;
            }

            if (i == RANK_BLACK_PAWN) {
                board.add(blackPawnRank);
                continue;
            }

            if (i == RANK_WHITE_PAWN) {
                board.add(whitePawnRank);
                continue;
            }

            if (i == RANK_WHITE_PIECE) {
                board.add(Rank.createPieceRank(Color.WHITE));
            } else {
                Rank blankRank = new Rank(new ArrayList<>());
                for (int j = 0; j < RANK_MAX_LENGTH; j++) {
                    String position = changePosToString(j, i);
                    blankRank.add(Piece.createBlank(new Position(position)));
                }
                board.add(blankRank);
            }
        }
    }

    public void initializeEmpty() {

        board.clear();

        for (int i = 0; i < FILE_MAX_LENGTH; i++) {
            Rank blankRank = new Rank(new ArrayList<>());
            for (int j = 0; j < RANK_MAX_LENGTH; j++) {
                String position = changePosToString(j, i);
                blankRank.add(Piece.createBlank(new Position(position)));
            }
            board.add(blankRank);
        }
    }

    private String changePosToString(int x, int y) {

        char pos_x = (char) ('a' + x);
        int pos_y = 8 - y;

        return String.valueOf(pos_x) + pos_y;
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

        //StringBuilder 사용해보기
        return board.stream()
                .map(rank -> rank.isEmpty() ? "........" : rank.representationRank())
                .collect(Collectors.joining(StringUtils.NEWLINE)) + StringUtils.NEWLINE;
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
        pieceList.sort(Comparator.comparingDouble(Piece::getDefaultPoint).reversed());
    }
}

