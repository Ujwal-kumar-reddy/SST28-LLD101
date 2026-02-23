public class StudentTaxCalculator implements TaxCalculator {
    @Override
    public double calculateTax(double subtotal) {
        return subtotal * 0.05;
    }

    @Override
    public double getTaxRate() {
        return 5.0;
    }
}