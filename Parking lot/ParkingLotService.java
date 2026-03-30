import java.util.*;

public class ParkingLotService {
    private final List<ParkingFloor> floors;
    private SlotAllocationStrategy slotStrategy;
    private FeeStrategy feeStrategy;
    private final Map<Integer, Ticket> activeTickets = new HashMap<>();

    public ParkingLotService(List<ParkingFloor> floors, SlotAllocationStrategy slotStrategy, FeeStrategy feeStrategy) {
        this.floors = floors;
        this.slotStrategy = slotStrategy;
        this.feeStrategy = feeStrategy;
    }

    public void setSlotStrategy(SlotAllocationStrategy s) { this.slotStrategy = s; }
    public void setFeeStrategy(FeeStrategy f) { this.feeStrategy = f; }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSlot slot = slotStrategy.allocate(floors, vehicle);
        if (slot == null) {
            System.out.println("No slot available for " + vehicle.number + " (" + vehicle.type + ")");
            return null;
        }
        slot.park(vehicle);
        Ticket ticket = new Ticket(vehicle, slot);
        activeTickets.put(ticket.ticketId, ticket);
        System.out.println("Parked vehicle " + vehicle.number + " at slot " + slot.slotId);
        return ticket;
    }

    public double unparkVehicle(int ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            System.out.println("Invalid ticket: " + ticketId);
            return 0.0;
        }
        long exitTime = System.currentTimeMillis();
        double fee = feeStrategy.calculateFee(ticket.entryTime, exitTime, ticket.vehicle.type);
        ticket.slot.removeVehicle();
        ticket.close(exitTime, fee);
        activeTickets.remove(ticketId);
        System.out.println("Unparked vehicle " + ticket.vehicle.number + "; fee=" + fee);
        return fee;
    }

    public Optional<Ticket> findActiveTicketByVehicle(String number) {
        return activeTickets.values().stream().filter(t -> t.vehicle.number.equals(number)).findFirst();
    }
}
