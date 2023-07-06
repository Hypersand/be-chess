package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private final List<Rank> board;

    public List<Piece> whitePawnList = new ArrayList<>();
    public List<Piece> blackPawnList = new ArrayList<>();
    public List<Piece> whitePieceList = new ArrayList<>();
    public List<Piece> blackPieceList = new ArrayList<>();
    public List<Piece> blankList = new ArrayList<>();

    public Rank whitePawnRank;
    public Rank blackPawnRank;

    public Board(List<Rank> board) {
        this.board = board;
    }

    public void add(Rank rank) {
        board.add(rank);
    }

    public Rank get(int index) {
        return board.get(index);
    }

    public void initialize() {

        whitePawnRank = Rank.createWhitePawnRank(whitePawnList);
        blackPawnRank = Rank.createBlackPawnRank(blackPawnList);

        Rank whitePieceRank = new Rank(whitePieceList);
        Rank blackPieceRank = new Rank(blackPieceList);

        Rank blankRank = new Rank(blankList);

        for (int i = 0; i < 8; i++) {
            blankRank.add(Piece.createBlank());
        }

        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                board.add(blackPieceRank);
                board.get(0).add(Piece.createPiece(Color.BLACK, Type.ROOK));
                board.get(0).add(Piece.createPiece(Color.BLACK, Type.KNIGHT));
                board.get(0).add(Piece.createPiece(Color.BLACK, Type.BISHOP));
                board.get(0).add(Piece.createPiece(Color.BLACK, Type.QUEEN));
                board.get(0).add(Piece.createPiece(Color.BLACK, Type.KING));
                board.get(0).add(Piece.createPiece(Color.BLACK, Type.BISHOP));
                board.get(0).add(Piece.createPiece(Color.BLACK, Type.KNIGHT));
                board.get(0).add(Piece.createPiece(Color.BLACK, Type.ROOK));
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
                board.add(whitePieceRank);
                board.get(7).add(Piece.createPiece(Color.WHITE, Type.ROOK));
                board.get(7).add(Piece.createPiece(Color.WHITE, Type.KNIGHT));
                board.get(7).add(Piece.createPiece(Color.WHITE, Type.BISHOP));
                board.get(7).add(Piece.createPiece(Color.WHITE, Type.QUEEN));
                board.get(7).add(Piece.createPiece(Color.WHITE, Type.KING));
                board.get(7).add(Piece.createPiece(Color.WHITE, Type.BISHOP));
                board.get(7).add(Piece.createPiece(Color.WHITE, Type.KNIGHT));
                board.get(7).add(Piece.createPiece(Color.WHITE, Type.ROOK));
                continue;
            } else {
                board.add(blankRank);
            }
        }
    }

    public int pieceCount() {

        return board.stream()
                .mapToInt(Rank::pieceCount)
                .sum();
    }

    public int pieceCount(Color color, Type type) {

        return board.stream()
                .mapToInt(rank -> rank.pieceCount(color, type))
                .sum();
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

    public void print() {
        System.out.println(this);
    }

    public String showBoard() {
        return this.toString();
    }

    public Piece findPiece(String pos) {

        String[] px_py = pos.split("");

        int pos_y = 8 - Integer.parseInt(px_py[1]);
        int pos_x = px_py[0].charAt(0) - 'a';

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

        Rank blankRank = new Rank(blankList);

        for (int i = 0; i < 8; i++) {
            blankRank.add(Piece.createBlank());
        }

        for (int i = 0; i < 8; i++) {
            board.add(blankRank);
        }
    }
}

