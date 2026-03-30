public class ParkingSlot {
    public final int slotId;
    public final VehicleType type;
    public boolean isOccupied;
    public Vehicle vehicle;
    public final double pricePerHour;
    public final int distance; // lower is nearer
    public final boolean vipReserved;

    public ParkingSlot(int slotId, VehicleType type, double pricePerHour, int distance, boolean vipReserved) {
        this.slotId = slotId;
        this.type = type;
        this.isOccupied = false;
        this.pricePerHour = pricePerHour;
        this.distance = distance;
        this.vipReserved = vipReserved;
    }

    public boolean canFit(Vehicle vehicle) {
        return !isOccupied && this.type == vehicle.type;
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isOccupied = false;
    }
}
