public class Ticket {
    private static int counter = 1;
    public final int ticketId;
    public final Vehicle vehicle;
    public final long entryTime;
    public ParkingSlot slot;
    public long exitTime = -1;
    public double fee = 0.0;

    public Ticket(Vehicle vehicle, ParkingSlot slot) {
        synchronized (Ticket.class) {
            this.ticketId = counter++;
        }
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = System.currentTimeMillis();
    }

    public void close(long exitTime, double fee) {
        this.exitTime = exitTime;
        this.fee = fee;
    }
}
