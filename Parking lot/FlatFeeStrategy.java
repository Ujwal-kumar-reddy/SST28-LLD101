public class FlatFeeStrategy implements FeeStrategy {
    private final double flatAmount;

    public FlatFeeStrategy(double flatAmount) {
        this.flatAmount = flatAmount;
    }

    @Override
    public double calculateFee(long entryTimeMillis, long exitTimeMillis, VehicleType vehicleType) {
        return flatAmount;
    }
}
