package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board extends ArrayList<ArrayList<Pawn>> {


    public List<Pawn> whitePawnList;
    public List<Pawn> blackPawnList;

    public Board() {
    }

    public Pawn findPawn(int row, int col) {
        return this.get(row).get(col);
    }

    public void initialize() {

        whitePawnList = new ArrayList<>();
        blackPawnList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            whitePawnList.add(new Pawn());
        }

        for (int i = 0; i < 8; i++) {
            blackPawnList.add(new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION));
        }

        for (int i = 0; i < 8; i++) {
            if (i == 1) {
                this.add((ArrayList<Pawn>) whitePawnList);
                continue;
            }

            if (i == 6) {
                this.add((ArrayList<Pawn>) blackPawnList);
                continue;
            }

            this.add(new ArrayList<>());
        }

    }

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();

        sbPawnList(whitePawnList, sb);

        return sb.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();

        sbPawnList(blackPawnList, sb);

        return sb.toString();
    }

    private void sbPawnList(List<Pawn> list, StringBuilder sb) {
        for (Pawn pawn : list) {
            sb.append(pawn.getRepresentation());
        }
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (List<Pawn> col : this) {
            if (col.isEmpty()) {
                sb.append("........").append("\n");
                continue;
            }

            for (Pawn pawn : col) {
                sb.append(pawn.getRepresentation());
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
