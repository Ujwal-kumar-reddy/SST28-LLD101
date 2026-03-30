import java.util.*;

class PenInventory {
    private List<Pen> pens = new ArrayList<>();

    public void addPen(Pen p) { pens.add(p); }

    public boolean removePen(Pen p) { return pens.remove(p); }

    public List<Pen> list() { return Collections.unmodifiableList(pens); }

    public List<Pen> findByBrand(String brand) {
        List<Pen> out = new ArrayList<>();
        for (Pen p : pens) {
            if (p.brand.equalsIgnoreCase(brand)) out.add(p);
        }
        return out;
    }

    public void refillPen(Pen p, int amount) {
        p.refillInk(amount);
    }
}
