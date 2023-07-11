package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.game.ChessGame;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.view.ChessView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.utils.StringUtils.appendNewLine;

class BoardTest {

    private Board board;
    private ChessView view;
    private ChessGame chessGame;

    @BeforeEach
    public void setUp() {
        List<Rank> boardList = new ArrayList<>();
        board = new Board(boardList);
        board.initialize();
        view = new ChessView();
        chessGame = new ChessGame(board);
    }

    @Test
    public void create() throws Exception {

        assertEquals(64, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                        appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                        view.showBoard(board)
        );
    }

    @Test
    public void initialize() throws Exception {
        assertEquals("pppppppp",board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    public void getWhitePawnsResult() {
        String whitePawnsResult = board.getWhitePawnsResult();
        assertEquals(whitePawnsResult, "pppppppp");
    }

    @Test
    public void getBlackPawnsResult() {
        String blackPawnsResult = board.getBlackPawnsResult();
        assertEquals(blackPawnsResult, "PPPPPPPP");
    }

    @Test
    public void getWhitePiecesResult() {
        String piecesRepresentation = board.get(7).representationRank();
        assertEquals(piecesRepresentation, "rnbqkbnr");
    }

    @Test
    public void getBlackPiecesResult() {
        String piecesRepresentation = board.get(0).representationRank();
        assertEquals(piecesRepresentation, "RNBQKBNR");
    }

    @Test
    @DisplayName("기물의 색과 종류로 기물의 개수를 반환한다.")
    public void getPieceCountByColorAndType() {
        assertEquals(32, board.pieceCount(Piece.Color.NOCOLOR, Piece.Type.NO_PIECE));
        assertEquals(8, board.pieceCount(Piece.Color.WHITE, Piece.Type.PAWN));
        assertEquals(8, board.pieceCount(Piece.Color.BLACK, Piece.Type.PAWN));
        assertEquals(2, board.pieceCount(Piece.Color.WHITE, Piece.Type.ROOK));
        assertEquals(2, board.pieceCount(Piece.Color.BLACK, Piece.Type.ROOK));
        assertEquals(2, board.pieceCount(Piece.Color.WHITE, Piece.Type.BISHOP));
        assertEquals(2, board.pieceCount(Piece.Color.BLACK, Piece.Type.BISHOP));
        assertEquals(2, board.pieceCount(Piece.Color.WHITE, Piece.Type.KNIGHT));
        assertEquals(2, board.pieceCount(Piece.Color.BLACK, Piece.Type.KNIGHT));
        assertEquals(1, board.pieceCount(Piece.Color.WHITE, Piece.Type.QUEEN));
        assertEquals(1, board.pieceCount(Piece.Color.BLACK, Piece.Type.QUEEN));
        assertEquals(1, board.pieceCount(Piece.Color.WHITE, Piece.Type.KING));
        assertEquals(1, board.pieceCount(Piece.Color.BLACK, Piece.Type.KING));
    }

    @Test
    @DisplayName("특정 위치의 기물을 반환해야 한다.")
    public void findPiece() throws Exception {
        assertThat(Piece.createPiece(Piece.Color.BLACK, Piece.Type.ROOK, new Position("a8")))
                .isEqualToComparingFieldByFieldRecursively(board.findPiece("a8"));
        assertThat(Piece.createPiece(Piece.Color.BLACK, Piece.Type.ROOK, new Position("h8")))
                .isEqualToComparingFieldByFieldRecursively(board.findPiece("h8"));
        assertThat(Piece.createPiece(Piece.Color.WHITE, Piece.Type.ROOK, new Position("a1")))
                .isEqualToComparingFieldByFieldRecursively(board.findPiece("a1"));
        assertThat(Piece.createPiece(Piece.Color.WHITE, Piece.Type.ROOK, new Position("h1")))
                .isEqualToComparingFieldByFieldRecursively(board.findPiece("h1"));
    }

    @Test
    @DisplayName("현재 남아있는 기물에 따른 점수 계산이 가능해야 한다.")
    public void caculcatePoint() throws Exception {

        board.initializeEmpty();
        addPiecesOnBoard();
        assertEquals(15.0, chessGame.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(8.5, chessGame.calculatePoint(Piece.Color.WHITE), 0.01);
        view.print(board);
    }

    @Test
    @DisplayName("같은 열에 있는 pawn에 대해서는 채점 방식이 달라야 한다.")
    public void caculcatePawn() throws Exception {

        board.initializeEmpty();
        addPiece("b6", Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN, new Position("b6")));
        addPiece("b3", Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN, new Position("b3")));
        addPiece("b1", Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN, new Position("b1")));
        assertEquals(1.5, chessGame.calculatePoint(Piece.Color.BLACK), 0.01);
        view.print(board);
    }

    private void addPiece(String position, Piece piece) {
        chessGame.move(position, piece);
    }

    @Test
    @DisplayName("체스판에 존재하는 흰색, 검정색 기물의 점수에 대해 내림차순으로 정렬")
    public void sortByDefaultPointDescending() {

        board.initializeEmpty();
        addPiecesOnBoard();

        List<Piece> whitePieceList = board.getPieceListByColor(Piece.Color.WHITE);
        board.sortByPointDescending(whitePieceList);
        List<Piece> blackPieceList = board.getPieceListByColor(Piece.Color.BLACK);
        board.sortByPointDescending(blackPieceList);

        assertThat(whitePieceList)
                .isSortedAccordingTo(Comparator.comparing(Piece::getDefaultPoint).reversed());

        assertThat(blackPieceList)
                .isSortedAccordingTo(Comparator.comparing(Piece::getDefaultPoint).reversed());
    }

    @Test
    @DisplayName("체스판에 존재하는 흰색, 검정색 기물의 점수에 대해 오름차순으로 정렬")
    public void sortByDefaultPointAscending() {

        board.initializeEmpty();
        addPiecesOnBoard();

        List<Piece> whitePieceList = board.getPieceListByColor(Piece.Color.WHITE);
        board.sortByPointAscending(whitePieceList);
        List<Piece> blackPieceList = board.getPieceListByColor(Piece.Color.BLACK);
        board.sortByPointAscending(blackPieceList);

        assertThat(whitePieceList)
                .isSortedAccordingTo(Comparator.comparing(Piece::getDefaultPoint));

        assertThat(blackPieceList)
                .isSortedAccordingTo(Comparator.comparing(Piece::getDefaultPoint));
    }

    private void addPiecesOnBoard() {
        addPiece("b6", Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN, new Position("b6")));
        addPiece("e6", Piece.createPiece(Piece.Color.BLACK, Piece.Type.QUEEN, new Position("e6")));
        addPiece("b8", Piece.createPiece(Piece.Color.BLACK, Piece.Type.KING, new Position("b8")));
        addPiece("c8", Piece.createPiece(Piece.Color.BLACK, Piece.Type.ROOK, new Position("c8")));

        addPiece("f2", Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN, new Position("f2")));
        addPiece("g2", Piece.createPiece(Piece.Color.WHITE, Piece.Type.KNIGHT, new Position("g2")));
        addPiece("e1", Piece.createPiece(Piece.Color.WHITE, Piece.Type.ROOK, new Position("e1")));
        addPiece("f1", Piece.createPiece(Piece.Color.WHITE, Piece.Type.KING, new Position("f1")));
    }

}