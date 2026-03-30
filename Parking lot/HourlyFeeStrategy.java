public class HourlyFeeStrategy implements FeeStrategy {
    private final double ratePerHour;

    public HourlyFeeStrategy(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    @Override
    public double calculateFee(long entryTimeMillis, long exitTimeMillis, VehicleType vehicleType) {
        long durationMs = Math.max(0, exitTimeMillis - entryTimeMillis);
        double hours = Math.ceil(durationMs / 3600000.0); // round up to next hour
        return hours * ratePerHour;
    }
}
