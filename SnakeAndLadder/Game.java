import java.util.*;

class Game {
    interface GameState { void play(Game game); }

    private final TurnManager turnManager;
    private final Dice dice;
    private final Board board;
    private final int WIN = 100;
    private final List<GameObserver> observers;
    private final EventStore eventStore;
    private GameState state;

    public Game(Board board, Dice dice, TurnManager turnManager, EventStore store, List<GameObserver> observers) {
        this.board = board;
        this.dice = dice;
        this.turnManager = turnManager;
        this.eventStore = store;
        this.observers = observers != null ? observers : new ArrayList<>();
        this.state = new InitState();
    }

    private void publish(String type, String actor, String message) {
        Event e = new Event(type, actor, message);
        eventStore.add(e);
        for (GameObserver o : observers) o.update(e);
    }

    public void addObserver(GameObserver o) { observers.add(o); }

    public void startGame() {
        setState(new InProgressState());
        state.play(this);
    }

    public void setState(GameState s) { this.state = s; }

    class InitState implements GameState { public void play(Game game) { publish("INFO", null, "Game initialized"); } }

    class InProgressState implements GameState {
        public void play(Game game) {
            boolean finished = false;

            while (!finished) {
                if (turnManager.isEmpty()) break;

                Player current = turnManager.getCurrent();
                if (current == null) break;

                if (current.shouldSkipNext()) {
                    current.setSkipNext(false);
                    publish("TURN", current.getName(), "is skipped this turn");
                    turnManager.advance(false);
                    publish("SEPARATOR", null, "----------------------");
                    continue;
                }

                int roll = dice.rollDice();
                publish("ROLL", current.getName(), "rolled: " + roll);

                int newPos = current.getPosition() + roll;

                if (newPos > WIN) {
                    publish("INFO", current.getName(), "Move exceeds " + WIN + ", skipping move");
                } else {
                    newPos = board.checkPosition(newPos);
                    current.setPosition(newPos);
                    publish("MOVE", current.getName(), "is now at " + newPos);
                }

                if (current.getPosition() == WIN) {
                    publish("WIN", current.getName(), "wins!");
                    finished = true;
                    setState(new FinishedState());
                    break;
                }

                if (roll != 6) {
                    turnManager.advance(false);
                } else {
                    publish("EXTRA", current.getName(), "gets an extra turn for rolling 6");
                }

                publish("SEPARATOR", null, "----------------------");
            }
        }
    }

    class FinishedState implements GameState { public void play(Game game) { publish("INFO", null, "Game finished"); } }
}