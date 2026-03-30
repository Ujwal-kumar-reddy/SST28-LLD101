class PenFactory {
    public static Pen createPen(String type) {
        if (type == null) return defaultFountain();

        if (type.equalsIgnoreCase("ball")) {
            return new Pen("Reynolds",
                    new Ink("Blue", 100),
                    new Tip(0.7),
                    new SmoothWriting());
        } else if (type.equalsIgnoreCase("gel")) {
            return new Pen("Pilot",
                    new Ink("Black", 100),
                    new Tip(0.5),
                    new SmoothWriting());
        } else if (type.equalsIgnoreCase("fountain")) {
            return new Pen("Parker",
                    new Ink("Blue", 100),
                    new Tip(0.6),
                    new RoughWriting());
        } else {
            return defaultFountain();
        }
    }

    private static Pen defaultFountain() {
        return new Pen("Parker",
                new Ink("Blue", 100),
                new Tip(0.6),
                new RoughWriting());
    }
}
