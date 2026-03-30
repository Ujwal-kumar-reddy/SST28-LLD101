class ExtendedState implements ClickState {
    public void click(ClickMechanism mech, Pen pen) {
        System.out.println("Retracting tip...");
        mech.setState(new RetractedState());
    }

    public boolean isExtended() { return true; }
}
