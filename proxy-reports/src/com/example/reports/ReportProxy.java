package com.example.reports;

/**
 * TODO (student):
 * Implement Proxy responsibilities here:
 * - access check
 * - lazy loading
 * - caching of RealReport within the same proxy
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    private RealReport realReport; // cached real subject

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // Access check
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED -> user=" + user.getName() + " role=" + user.getRole()
                    + " cannot open report " + reportId + "(" + classification + ")");
            return;
        }

        // lazy create and reuse RealReport
        if (realReport == null) {
            System.out.println("[proxy] creating real report for " + reportId);
            realReport = new RealReport(reportId, title, classification);
        }
        realReport.display(user);
    }
}
