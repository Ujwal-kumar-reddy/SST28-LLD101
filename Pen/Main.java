public class Main {
    public static void main(String[] args) {
        Pen pen1 = PenFactory.createPen("ball");
        pen1.write("Hello World");

        Pen customPen = new PenBuilder()
                .setBrand("Custom")
                .setInk(new Ink("Red", 50))
                .setTip(new Tip(0.5))
                .setStrategy(new SmoothWriting())
                .build();

        customPen.write("Custom Writing");

        System.out.println("--- Demo: Click Mechanism ---");
        pen1.click(); // extend
        pen1.write("Now it writes after extend");
        pen1.click(); // retract
        pen1.write("Should not write when retracted");

        System.out.println("--- Demo: Observer (leak) and Refill ---");
        InkLeakLogger logger = new InkLeakLogger();
        pen1.addObserver(logger);

        // simulate low ink notifications by repeatedly writing
        for (int i = 0; i < 98; i++) {
            pen1.click(); // ensure extended
            pen1.write("Line " + i);
        }

        // simulate sudden leak
        customPen.addObserver(logger);
        customPen.simulateLeak();

        System.out.println("Refilling custom pen by 30");
        customPen.refillInk(30);
        System.out.println("Custom pen ink level after refill: " + customPen.getInk().getLevel());

        System.out.println("--- Demo: Inventory ---");
        PenInventory inv = new PenInventory();
        inv.addPen(pen1);
        inv.addPen(customPen);
        inv.addPen(PenFactory.createPen("gel"));

        for (Pen p : inv.list()) {
            System.out.println("Inventory: " + p.brand + " (ink=" + p.getInk().getLevel() + ")");
        }

    }
}
