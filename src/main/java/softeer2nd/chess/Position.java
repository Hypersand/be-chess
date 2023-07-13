package softeer2nd.chess;

import softeer2nd.chess.exception.InvalidPositionException;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private static final int BOARD_LENGTH = 8;
    private static final int RANK_WHITE_PAWN = 6;
    private static final int RANK_BLACK_PAWN = 1;

    private int rank;
    private int file;

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    public Position(String position) {

        Map<String, Integer> posMap = calculatePosition(position);

        this.rank = posMap.get("rank");
        this.file = posMap.get("file");
    }

    public Position(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public void movePosition(String position) {
        Map<String, Integer> posMap = calculatePosition(position);
        this.rank = posMap.get("rank");
        this.file = posMap.get("file");
    }

    private Map<String, Integer> calculatePosition(String position) {

        Map<String, Integer> map = new HashMap<>();

        String[] positions = position.split("");
        int position_rank = BOARD_LENGTH - Integer.parseInt(positions[1]);
        int position_file = positions[0].charAt(0) - 'a';

        map.put("rank", position_rank);
        map.put("file", position_file);

        return map;
    }

    public void verifyTargetPosition() {
        if (rank < 0 || rank > 7) {
            throw new InvalidPositionException("체스판 위에 말을 배치해 주세요!");
        }
        if (file < 0 || file > 7) {
            throw new InvalidPositionException("체스판 위에 말을 배치해 주세요!");
        }
    }

    public boolean isWhitePawnStartPosition() {
        return getRank() == RANK_WHITE_PAWN;
    }

    public boolean isBlackPawnStartPosition() {
        return getRank() == RANK_BLACK_PAWN;
    }

}
