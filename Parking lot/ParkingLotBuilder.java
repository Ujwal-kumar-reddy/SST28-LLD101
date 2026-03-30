import java.util.ArrayList;
import java.util.List;

public class ParkingLotBuilder {
    private final List<ParkingFloor> floors = new ArrayList<>();

    public ParkingLotBuilder addFloor(ParkingFloor floor) {
        floors.add(floor);
        return this;
    }

    public ParkingLot build() {
        return new ParkingLot(floors);
    }
}
