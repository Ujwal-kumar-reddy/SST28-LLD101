import java.util.Map;

public class VehicleBasedFeeStrategy implements FeeStrategy {
    private final Map<VehicleType, Double> ratePerHourByType;

    public VehicleBasedFeeStrategy(Map<VehicleType, Double> ratePerHourByType) {
        this.ratePerHourByType = ratePerHourByType;
    }

    @Override
    public double calculateFee(long entryTimeMillis, long exitTimeMillis, VehicleType vehicleType) {
        long durationMs = Math.max(0, exitTimeMillis - entryTimeMillis);
        double hours = Math.ceil(durationMs / 3600000.0);
        double rate = ratePerHourByType.getOrDefault(vehicleType, 0.0);
        return hours * rate;
    }
}
