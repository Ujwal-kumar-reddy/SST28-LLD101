class PenBuilder {
    String brand;
    Ink ink;
    Tip tip;
    WritingStrategy strategy;

    public PenBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public PenBuilder setInk(Ink ink) {
        this.ink = ink;
        return this;
    }

    public PenBuilder setTip(Tip tip) {
        this.tip = tip;
        return this;
    }

    public PenBuilder setStrategy(WritingStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public Pen build() {
        String b = (brand != null) ? brand : "Custom";
        Ink i = (ink != null) ? ink : new Ink("Black", 100);
        Tip t = (tip != null) ? tip : new Tip(0.5);
        WritingStrategy s = (strategy != null) ? strategy : new SmoothWriting();
        return new Pen(b, i, t, s);
    }
}
