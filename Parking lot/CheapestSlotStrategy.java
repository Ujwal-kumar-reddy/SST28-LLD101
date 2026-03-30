import java.util.List;

public class CheapestSlotStrategy implements SlotAllocationStrategy {
    @Override
    public ParkingSlot allocate(List<ParkingFloor> floors, Vehicle vehicle) {
        ParkingSlot best = null;
        for (ParkingFloor floor : floors) {
            for (ParkingSlot slot : floor.getSlots()) {
                if (slot.canFit(vehicle)) {
                    if (best == null || slot.pricePerHour < best.pricePerHour) {
                        best = slot;
                    }
                }
            }
        }
        return best;
    }
}
