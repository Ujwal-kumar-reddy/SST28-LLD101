import java.util.*;

public class EventStore {
    private final List<Event> events = new ArrayList<>();

    public void add(Event e) { events.add(e); }

    public List<Event> all() { return Collections.unmodifiableList(events); }
}
