class ClickMechanism {
    private ClickState state;

    public ClickMechanism() {
        this.state = new RetractedState();
    }

    public void setState(ClickState s) { this.state = s; }

    public void click(Pen pen) { state.click(this, pen); }

    public boolean isExtended() { return state.isExtended(); }
}
