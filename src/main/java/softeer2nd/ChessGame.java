package softeer2nd;

import softeer2nd.chess.Board;

import java.util.ArrayList;
import java.util.Scanner;

public class ChessGame {

    public static void run() {

        Scanner sc = new Scanner(System.in);

        Board board = new Board(new ArrayList<>());

        while (sc.hasNextLine()) {

            String next = sc.nextLine();

            if (next.equals("start")) {
                System.out.println("체스 게임을 시작합니다.");
                board.initialize();
                board.print();
            }

            if (next.equals("end")) {
                System.out.println("체스 게임을 종료합니다.");
                break;
            }

            if (next.startsWith("move")) {
                String[] input = next.split(" ");
                board.move(input[1], input[2]);
                System.out.println(board.showBoard());
            }

        }

    }
}
