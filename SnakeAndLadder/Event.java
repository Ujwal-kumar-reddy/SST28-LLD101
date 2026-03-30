public class Event {
    public final String type;
    public final String actor;
    public final String message;
    public final long timestamp;

    public Event(String type, String actor, String message) {
        this.type = type;
        this.actor = actor;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + type + ": " + (actor != null ? actor + " - " : "") + message;
    }
}
