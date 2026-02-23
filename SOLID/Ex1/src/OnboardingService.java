import java.util.*;

public class OnboardingService {
    private final StudentRepository repository;
    private final InputParser parser;
    private final StudentValidator validator;

    public OnboardingService(StudentRepository repository) {
        this.repository = repository;
        this.parser = new InputParser();
        this.validator = new StudentValidator();
    }

    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        Map<String, String> data = parser.parse(raw);
        List<String> errors = validator.validate(data);

        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            for (String e : errors) System.out.println("- " + e);
            return;
        }

        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(
            id, 
            data.get("name"), 
            data.get("email"), 
            data.get("phone"), 
            data.get("program")
        );

        repository.save(rec);

        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + repository.count());
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}