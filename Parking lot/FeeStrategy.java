public interface FeeStrategy {
    double calculateFee(long entryTimeMillis, long exitTimeMillis, VehicleType vehicleType);
}
