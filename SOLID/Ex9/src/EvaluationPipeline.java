public class EvaluationPipeline {
    // DIP compliant: high-level module depends on abstractions
    private final PlagiarismCheckService plagiarismChecker;
    private final CodeGradingService codeGrader;
    private final ReportWritingService reportWriter;
    private final Rubric rubric;

    public EvaluationPipeline(PlagiarismCheckService plagiarismChecker, 
                              CodeGradingService codeGrader, 
                              ReportWritingService reportWriter,
                              Rubric rubric) {
        this.plagiarismChecker = plagiarismChecker;
        this.codeGrader = codeGrader;
        this.reportWriter = reportWriter;
        this.rubric = rubric;
    }

    public void evaluate(Submission sub) {
        int plag = plagiarismChecker.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = codeGrader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = reportWriter.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
