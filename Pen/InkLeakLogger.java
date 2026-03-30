class InkLeakLogger implements InkLeakObserver {
    public void onLeak(Pen pen, String reason) {
        System.out.println("[LeakObserver] Pen brand=" + pen.brand + ", reason=" + reason);
    }
}
