package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.utils.StringUtils.appendNewLine;

class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        List<Rank> boardList = new ArrayList<>();
        board = new Board(boardList);
        board.initialize();
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
                        board.showBoard()
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
    public void print() {

        String expectPrint = "RNBQKBNR" + "\n"
                + "PPPPPPPP" + "\n"
                + "........" + "\n"
                + "........" + "\n"
                + "........" + "\n"
                + "........" + "\n"
                + "pppppppp" + "\n"
                + "rnbqkbnr" + "\n";
        assertEquals(expectPrint, board.toString());
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

        assertEquals(Piece.createPiece(Piece.Color.BLACK, Piece.Type.ROOK), board.findPiece("a8"));
        assertEquals(Piece.createPiece(Piece.Color.BLACK, Piece.Type.ROOK), board.findPiece("h8"));
        assertEquals(Piece.createPiece(Piece.Color.WHITE, Piece.Type.ROOK), board.findPiece("a1"));
        assertEquals(Piece.createPiece(Piece.Color.WHITE, Piece.Type.ROOK), board.findPiece("h1"));
    }

    @Test
    @DisplayName("특정 위치에 기물을 추가할 수 있어야 한다.")
    public void move() throws Exception {

        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createPiece(Piece.Color.BLACK, Piece.Type.ROOK);
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("현재 남아있는 기물에 따른 점수 계산이 가능해야 한다.")
    public void caculcatePoint() throws Exception {

        board.initializeEmpty();

        addPiece("b6", Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN));
        addPiece("e6", Piece.createPiece(Piece.Color.BLACK, Piece.Type.QUEEN));
        addPiece("b8", Piece.createPiece(Piece.Color.BLACK, Piece.Type.KING));
        addPiece("c8", Piece.createPiece(Piece.Color.BLACK, Piece.Type.ROOK));

        addPiece("f2", Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN));
        addPiece("g2", Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN));
        addPiece("e1", Piece.createPiece(Piece.Color.WHITE, Piece.Type.ROOK));
        addPiece("f1", Piece.createPiece(Piece.Color.WHITE, Piece.Type.KING));

        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Piece.Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("같은 열에 있는 pawn에 대해서는 채점 방식이 달라야 한다.")
    public void caculcatePawn() throws Exception {

        board.initializeEmpty();

        addPiece("b6", Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN));
        addPiece("b3", Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN));
        addPiece("b1", Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN));

        addPiece("c3", Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN));
        addPiece("c1", Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN));
        addPiece("c6", Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN));
        addPiece("c2", Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN));

        assertEquals(1.5, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(2.0, board.calculatePoint(Piece.Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }

}