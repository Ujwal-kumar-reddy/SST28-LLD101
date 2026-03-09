public class TransportBookingService {
    // DIP compliant: depends on abstractions
    private final DistanceCalculationService distanceCalculator;
    private final DriverAllocationService driverAllocator;
    private final PaymentService paymentGateway;
    private final FareCalculationService fareCalculator;

    public TransportBookingService(DistanceCalculationService distanceCalculator,
                                    DriverAllocationService driverAllocator,
                                    PaymentService paymentGateway,
                                    FareCalculationService fareCalculator) {
        this.distanceCalculator = distanceCalculator;
        this.driverAllocator = driverAllocator;
        this.paymentGateway = paymentGateway;
        this.fareCalculator = fareCalculator;
    }

    public void book(TripRequest req) {
        double km = distanceCalculator.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = driverAllocator.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = fareCalculator.calculateFare(km);

        String txn = paymentGateway.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
