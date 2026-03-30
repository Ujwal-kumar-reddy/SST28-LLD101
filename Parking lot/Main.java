import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        // Build parking lot: 2 floors
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSlot(new ParkingSlot(1, VehicleType.CAR, 10.0, 5, false));
        floor1.addSlot(new ParkingSlot(2, VehicleType.CAR, 8.0, 8, true)); // VIP
        floor1.addSlot(new ParkingSlot(3, VehicleType.BIKE, 2.0, 3, false));

        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSlot(new ParkingSlot(4, VehicleType.TRUCK, 20.0, 20, false));
        floor2.addSlot(new ParkingSlot(5, VehicleType.CAR, 6.0, 25, false));

        ParkingLotBuilder builder = new ParkingLotBuilder();
        builder.addFloor(floor1).addFloor(floor2);

        // Strategies
        SlotAllocationStrategy nearest = new NearestSlotStrategy();
        SlotAllocationStrategy cheapest = new CheapestSlotStrategy();
        SlotAllocationStrategy vip = new VipSlotStrategy();

        FeeStrategy hourly = new HourlyFeeStrategy(10.0);
        FeeStrategy flat = new FlatFeeStrategy(15.0);
        Map<VehicleType, Double> map = new HashMap<>();
        map.put(VehicleType.BIKE, 3.0);
        map.put(VehicleType.CAR, 10.0);
        map.put(VehicleType.TRUCK, 25.0);
        FeeStrategy byVehicle = new VehicleBasedFeeStrategy(map);

        // Service using nearest allocation and hourly fee
        ParkingLotService service = new ParkingLotService(builder.build().floors, nearest, hourly);

        Vehicle v1 = VehicleFactory.createVehicle("KA-01-1111", VehicleType.CAR);
        Vehicle v2 = VehicleFactory.createVehicle("KA-01-2222", VehicleType.BIKE);
        Vehicle v3 = VehicleFactory.createVehicle("KA-01-3333", VehicleType.TRUCK);

        Ticket t1 = service.parkVehicle(v1);
        Ticket t2 = service.parkVehicle(v2);
        Ticket t3 = service.parkVehicle(v3);

        // Simulate time by adjusting entryTime (for demo only)
        if (t1 != null) t1.close(System.currentTimeMillis() + 3600_000L * 2, 0); // fake exit 2 hours later

        // Unpark using different fee strategies
        if (t1 != null) {
            // override fee strategy to vehicle-based
            service.setFeeStrategy(byVehicle);
            double fee = service.unparkVehicle(t1.ticketId);
            System.out.println("Fee for " + t1.vehicle.number + " = " + fee);
        }

        if (t2 != null) {
            // use flat fee
            service.setFeeStrategy(flat);
            double fee = service.unparkVehicle(t2.ticketId);
            System.out.println("Fee for " + t2.vehicle.number + " = " + fee);
        }

        if (t3 != null) {
            service.setFeeStrategy(hourly);
            double fee = service.unparkVehicle(t3.ticketId);
            System.out.println("Fee for " + t3.vehicle.number + " = " + fee);
        }
    }
}
