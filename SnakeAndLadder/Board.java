import java.util.HashMap;
import java.util.Map;

class Board {
    Map<Integer, Integer> snakes = new HashMap<>();
    Map<Integer, Integer> ladders = new HashMap<>();

    public Board() {
        // Add snakes
        snakes.put(99, 10);
        snakes.put(95, 56);
        snakes.put(70, 55);

        // Add ladders
        ladders.put(2, 38);
        ladders.put(7, 14);
        ladders.put(20, 42);
    }

    public int checkPosition(int pos) {
        if (snakes.containsKey(pos)) {
            System.out.println("Bitten by snake! Down to " + snakes.get(pos));
            return snakes.get(pos);
        } else if (ladders.containsKey(pos)) {
            System.out.println("Climbed a ladder! Up to " + ladders.get(pos));
            return ladders.get(pos);
        }
        return pos;
    }
}