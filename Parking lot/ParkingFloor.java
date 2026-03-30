import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    public final int floorNumber;
    public final List<ParkingSlot> slots = new ArrayList<>();

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void addSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }
}
