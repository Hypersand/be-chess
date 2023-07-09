package softeer2nd.chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Position {

    public static final int BOARD_LENGTH = 8;

    private int rank;
    private int file;

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

    public List<String> getWhitePawnPosition() {

        List<String> whitePawnPositionList = new ArrayList<>();

        for (int i = 0; i < BOARD_LENGTH; i++) {
            String position = String.valueOf((char) ('a' + i)) + (2);
            whitePawnPositionList.add(position);
        }

        return whitePawnPositionList;
    }

}
