import java.util.*;

class GameBuilder {
    private Board board = new Board();
    private Dice dice = new Dice(new NormalDice());
    private TurnManager turnManager = new DequeTurnManager();
    private EventStore eventStore = new EventStore();
    private List<GameObserver> observers = new ArrayList<>();

    public GameBuilder addPlayer(String name) {
        turnManager.addPlayer(new Player(name));
        return this;
    }

    public GameBuilder setDiceStrategy(DiceStrategy ds) {
        this.dice = new Dice(ds);
        return this;
    }

    public GameBuilder setBoard(Board b) { this.board = b; return this; }

    public GameBuilder addObserver(GameObserver o) { observers.add(o); return this; }

    public Game build() {
        Game game = new Game(board, dice, turnManager, eventStore, observers);
        return game;
    }
}
