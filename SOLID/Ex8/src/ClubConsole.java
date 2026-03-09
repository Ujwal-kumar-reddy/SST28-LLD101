public class ClubConsole {
    private final BudgetLedger ledger;
    private final MinutesBook minutes;
    private final EventPlanner events;

    public ClubConsole(BudgetLedger ledger, MinutesBook minutes, EventPlanner events) {
        this.ledger = ledger; this.minutes = minutes; this.events = events;
    }

    public void run() {
        // Create tools that implement only what they need
        FinanceManagement treasurer = new TreasurerTool(ledger);
        MinutesManagement secretary = new SecretaryTool(minutes);
        EventManagement lead = new EventLeadTool(events);

        // Use only the specific capabilities each tool provides
        treasurer.addIncome(5000, "sponsor");
        secretary.addMinutes("Meeting at 5pm");
        lead.createEvent("HackNight", 2000);

        System.out.println("Summary: ledgerBalance=" + ledger.balanceInt() + ", minutes=" + minutes.count() + ", events=" + lead.getEventsCount());
    }
}
