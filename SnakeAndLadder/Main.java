public class Main {
    public static void main(String[] args) {
        GameBuilder builder = new GameBuilder();
        builder.addPlayer("Alice").addPlayer("Bob").setDiceStrategy(new NormalDice()).addObserver(new ConsoleLogger());
        Game game = builder.build();

        game.startGame();
    }
}