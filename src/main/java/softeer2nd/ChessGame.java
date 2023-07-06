package softeer2nd;

import softeer2nd.chess.Board;
import softeer2nd.chess.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessGame {

    public static void run() {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            String next = sc.next();

            if (next.equals("start")) {
                System.out.println("체스 게임을 시작합니다.");
                List<Rank> boardList = new ArrayList<>();
                Board board = new Board(boardList);
                board.initialize();
                board.print();
            }

            if (next.equals("end")) {
                System.out.println("체스 게임을 종료합니다.");
                break;
            }

        }

    }
}
