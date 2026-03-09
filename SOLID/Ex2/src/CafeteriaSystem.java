import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final InvoiceFormatter formatter = new InvoiceFormatter();
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store) {
        this.store = store;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(TaxCalculator taxCalc, DiscountCalculator discountCalc, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        List<String> itemLines = new ArrayList<>();
        double subtotal = 0.0;

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            itemLines.add(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        double tax = taxCalc.calculateTax(subtotal);
        double discount = discountCalc.calculateDiscount(subtotal, lines.size());
        double total = subtotal + tax - discount;

        String printable = formatter.format(invId, itemLines, subtotal, taxCalc.getTaxRate(), tax, discount, total);
        
        System.out.print(printable);
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}