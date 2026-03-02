public class FareCalculator implements FareCalculationService {
    public double calculateFare(double distanceKm) {
        double fare = 50.0 + distanceKm * 6.6666666667;
        fare = Math.round(fare * 100.0) / 100.0;
        return fare;
    }
}
