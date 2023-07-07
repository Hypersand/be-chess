package softeer2nd.chess;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private int x;
    private int y;

    public Position(String position) {

        Map<String, Integer> posMap = calculatePosition(position);

        this.x = posMap.get("x");
        this.y = posMap.get("y");
    }

    private Map<String, Integer> calculatePosition(String position) {

        Map<String, Integer> map = new HashMap<>();

        String[] px_py = position.split("");
        int pos_x = px_py[0].charAt(0) - 'a';
        int pos_y = 8 - Integer.parseInt(px_py[1]);

        map.put("x", pos_x);
        map.put("y", pos_y);

        return map;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public void movePosition(String position) {
        Map<String, Integer> posMap = calculatePosition(position);
        this.x = posMap.get("x");
        this.y = posMap.get("y");
    }

}
