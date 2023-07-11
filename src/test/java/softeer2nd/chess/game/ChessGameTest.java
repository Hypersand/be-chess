package softeer2nd.chess.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.Position;
import softeer2nd.chess.Rank;
import softeer2nd.chess.exception.InvalidMovementException;
import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    public void setUp() {
        List<Rank> boardList = new ArrayList<>();
        board = new Board(boardList);
        board.initialize();
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("기물이 현재 위치에서 다른 위치로 이동할 수 있어야 한다.")
    public void moveToTarget() throws Exception {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        chessGame.move(sourcePosition, targetPosition);
        assertThat(Piece.createPiece(Piece.Color.NOCOLOR, Piece.Type.NO_PIECE, new Position(sourcePosition)))
                .isEqualToComparingFieldByFieldRecursively(board.findPiece(sourcePosition));
        assertThat(Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN, new Position(targetPosition)))
                .isEqualToComparingFieldByFieldRecursively(board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("폰은 기물이 없는 곳으로 대각선 이동할 수 없다.")
    void pawn_move_diagonal() {

        //given
        board.initializeEmpty();
        String sourcePos = "b3";
        String targetPos = "c4";
        Piece pawn = Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN, new Position(sourcePos));
        chessGame.move(sourcePos, pawn);

        //when,then
        assertThatThrownBy(() -> chessGame.move(sourcePos, targetPos))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("폰은 기물이 없는 곳으로 대각선 이동할 수 없습니다!");
    }

    @Test
    @DisplayName("폰은 기물이 있는 곳으로 직선 이동할 수 없다.")
    void pawn_move_linear() {

        //given
        board.initializeEmpty();
        String sourcePos = "b3";
        String targetPos = "b4";
        Piece sourcePawn = Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN, new Position(sourcePos));
        Piece targetPawn = Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN, new Position(targetPos));
        chessGame.move(sourcePos, sourcePawn);
        chessGame.move(targetPos, targetPawn);

        //when,then
        assertThatThrownBy(() -> chessGame.move(sourcePos, targetPos))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("폰은 기물이 있는 곳으로 직선 이동할 수 없습니다!");
    }

    @Test
    @DisplayName("배치하려는 위치의 경로 중간에 말이 있으면 이동할 수 없다.")
    void move_path_obstructed() {

        //given
        board.initializeEmpty();
        String sourcePos = "d5";
        String middlePos = "c6";
        String targetPos = "b7";
        Piece sourceBishop = Piece.createPiece(Piece.Color.WHITE, Piece.Type.BISHOP, new Position(sourcePos));
        Piece middlePawn = Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN, new Position(middlePos));
        chessGame.move(sourcePos, sourceBishop);
        chessGame.move(middlePos, middlePawn);

        //when,then
        assertThatThrownBy(() -> chessGame.move(sourcePos, targetPos))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("배치하려는 위치의 경로 중간에 말이 있으면 이동할 수 없습니다!");

    }

}