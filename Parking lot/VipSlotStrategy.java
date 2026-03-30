import java.util.List;

public class VipSlotStrategy implements SlotAllocationStrategy {
    @Override
    public ParkingSlot allocate(List<ParkingFloor> floors, Vehicle vehicle) {
        ParkingSlot fallback = null;
        for (ParkingFloor floor : floors) {
            for (ParkingSlot slot : floor.getSlots()) {
                if (slot.canFit(vehicle)) {
                    if (slot.vipReserved) {
                        return slot; // immediate preference
                    }
                    if (fallback == null) fallback = slot;
                }
            }
        }
        return fallback;
    }
}
