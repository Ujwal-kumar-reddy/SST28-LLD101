import java.util.*;

class NormalDice implements DiceStrategy {
    private static final Random RAND = new Random();

    public int roll() {
        return RAND.nextInt(6) + 1;
    }
}
