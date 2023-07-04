package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        return representationPawnList(whitePawnList, Pawn.WHITE_COLOR);
    }

    public String getBlackPawnsResult() {

        return representationPawnList(blackPawnList, Pawn.BLACK_COLOR);
    }

    private String representationPawnList(List<Pawn> pawnList, String color) {

        StringBuilder sb = new StringBuilder();

        return pawnList.stream()
                .filter(pawn -> pawn.getColor().equals(color))
                .map(pawn -> String.valueOf(pawn.getRepresentation()))
                .collect(Collectors.joining());
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {

        return this.stream()
                .map(col -> col.isEmpty() ? "........" : col.stream()
                        .map(pawn -> String.valueOf(pawn.getRepresentation()))
                        .collect(Collectors.joining())
                )
                .collect(Collectors.joining("\n"));
    }
}

