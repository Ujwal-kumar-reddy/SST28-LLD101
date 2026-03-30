class Pen {
    String brand;
    Ink ink;
    Tip tip;
    WritingStrategy writingStrategy;
    ClickMechanism clickMechanism;
    java.util.List<InkLeakObserver> observers = new java.util.ArrayList<>();

    public Pen(String brand, Ink ink, Tip tip, WritingStrategy strategy) {
        this.brand = brand;
        this.ink = ink;
        this.tip = tip;
        this.writingStrategy = strategy;
        this.clickMechanism = new ClickMechanism();
    }

    public void write(String text) {
        if (clickMechanism.isExtended()) {
            writingStrategy.write(text);
            ink.useInk();
            checkLowInk();
        } else {
            System.out.println("Tip is retracted. Click to extend before writing.");
        }
    }

    public void refillInk(int amount) {
        ink.refill(amount);
    }

    public void simulateLeak() {
        ink.leak();
        notifyLeakage("Ink leaked suddenly");
    }

    public void addObserver(InkLeakObserver o) {
        observers.add(o);
    }

    public void removeObserver(InkLeakObserver o) {
        observers.remove(o);
    }

    private void notifyLeakage(String reason) {
        for (InkLeakObserver o : observers) {
            o.onLeak(this, reason);
        }
    }

    private void checkLowInk() {
        if (ink.getLevel() <= 5) {
            notifyLeakage("Low ink: " + ink.getLevel());
        }
    }

    public void click() {
        clickMechanism.click(this);
    }

    public boolean isTipExtended() {
        return clickMechanism.isExtended();
    }

    public Ink getInk() {
        return ink;
    }
}