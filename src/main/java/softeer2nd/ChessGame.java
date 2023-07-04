package softeer2nd;

import softeer2nd.chess.Board;

import java.util.Scanner;

public class ChessGame {

    public static void run() {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            String next = sc.next();

            if (next.equals("start")) {
                System.out.println("체스 게임을 시작합니다.");
                Board board = new Board();
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
