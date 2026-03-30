import java.util.Deque;
import java.util.LinkedList;

public class DequeTurnManager implements TurnManager {
    private final Deque<Player> deque = new LinkedList<>();
    private boolean forward = true;

    @Override
    public void addPlayer(Player p) { deque.offerLast(p); }

    @Override
    public Player getCurrent() {
        return forward ? deque.peekFirst() : deque.peekLast();
    }

    @Override
    public void advance(boolean keepTurn) {
        if (deque.isEmpty()) return;
        if (keepTurn) return; // do nothing

        if (forward) {
            Player p = deque.pollFirst();
            deque.offerLast(p);
        } else {
            Player p = deque.pollLast();
            deque.offerFirst(p);
        }
    }

    @Override
    public void skipNextFor(String playerName) {
        for (Player p : deque) {
            if (p.getName().equals(playerName)) {
                p.setSkipNext(true);
                return;
            }
        }
    }

    @Override
    public void reverse() {
        forward = !forward;
    }

    @Override
    public boolean isEmpty() { return deque.isEmpty(); }
}
