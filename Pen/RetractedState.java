class RetractedState implements ClickState {
    public void click(ClickMechanism mech, Pen pen) {
        System.out.println("Extending tip...");
        mech.setState(new ExtendedState());
    }

    public boolean isExtended() { return false; }
}
