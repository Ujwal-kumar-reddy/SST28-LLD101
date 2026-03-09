public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.93, 77.62));
        
        // DIP compliant: wire dependencies
        DistanceCalculationService distanceCalculator = new DistanceCalculator();
        DriverAllocationService driverAllocator = new DriverAllocator();
        PaymentService paymentGateway = new PaymentGateway();
        FareCalculationService fareCalculator = new FareCalculator();
        
        TransportBookingService svc = new TransportBookingService(distanceCalculator, driverAllocator, paymentGateway, fareCalculator);
        svc.book(req);
    }
}
