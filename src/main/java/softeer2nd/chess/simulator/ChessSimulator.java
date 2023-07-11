package softeer2nd.chess.simulator;

import softeer2nd.chess.Board;
import softeer2nd.chess.game.ChessGame;
import softeer2nd.chess.game.Turn;
import softeer2nd.chess.view.ChessView;

import java.util.ArrayList;
import java.util.Scanner;

public class ChessSimulator {

    public static void run() {

        Scanner sc = new Scanner(System.in);

        Board board = new Board(new ArrayList<>());
        Turn turn = new Turn();
        ChessView view = new ChessView();
        ChessGame chessGame = new ChessGame(board, turn);

        while (sc.hasNextLine()) {

            String next = sc.nextLine();

            if (next.equals("start")) {
                System.out.println("체스 게임을 시작합니다.");
                board.initialize();
                view.print(board);
            }

            if (next.equals("end")) {
                System.out.println("체스 게임을 종료합니다.");
                break;
            }

            if (next.startsWith("move")) {
                String[] input = next.split(" ");
                chessGame.move(input[1], input[2]);
                view.print(board);
            }

        }

    }
}
