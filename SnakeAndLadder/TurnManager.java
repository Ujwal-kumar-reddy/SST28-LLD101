public interface TurnManager {
    void addPlayer(Player p);
    Player getCurrent();
    void advance(boolean keepTurn);
    void skipNextFor(String playerName);
    void reverse();
    boolean isEmpty();
}
