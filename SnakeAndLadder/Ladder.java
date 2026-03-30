class Ladder implements BoardEntity {
    int start, end;

    public Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int move(int position) {
        return position == start ? end : position;
    }
}