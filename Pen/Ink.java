class Ink {
    String color;
    int level;

    public Ink(String color, int level) {
        this.color = color;
        this.level = level;
    }

    public void useInk() {
        if (level > 0) {
            level--;
        } else {
            System.out.println("Ink finished!");
        }
    }

    public void refill(int amount) {
        if (amount > 0) {
            level += amount;
        }
    }

    public void leak() {
        // simulate sudden leak
        level = 0;
    }

    public int getLevel() {
        return level;
    }
}