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
                    blankRank.add(Piece.createBlank());
                }
                board.add(blankRank);
            }
        }
    }

    private Rank createPieceRank(Color color) {
        List<Piece> pieces = Arrays.asList(
                Piece.createPiece(color, Type.ROOK),
                Piece.createPiece(color, Type.KNIGHT),
                Piece.createPiece(color, Type.BISHOP),
                Piece.createPiece(color, Type.QUEEN),
                Piece.createPiece(color, Type.KING),
                Piece.createPiece(color, Type.BISHOP),
                Piece.createPiece(color, Type.KNIGHT),
                Piece.createPiece(color, Type.ROOK)
        );
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
                blankRank.add(Piece.createBlank());
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

