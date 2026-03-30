class ConsoleLogger implements GameObserver {
    public void update(Event event) {
        System.out.println(event.toString());
    }
}
