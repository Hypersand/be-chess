package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;

public class Board extends ArrayList<Pawn> {

    public Board() {
    }

    public Pawn findPawn(int index) {
        return this.get(index);
    }
}
