public class Player {
    private final String name;
    private int position;
    private boolean skipNext = false;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() { return name; }
    public int getPosition() { return position; }
    public void setPosition(int pos) { this.position = pos; }

    public void setSkipNext(boolean skip) { this.skipNext = skip; }
    public boolean shouldSkipNext() { return skipNext; }
}