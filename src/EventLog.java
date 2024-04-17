import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLog {

    private static final String INPUT_NOT_VALID = "Description should not be empty";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
    private final String description;
    private final LocalDateTime timestamp;

    public EventLog() {
        throw new IllegalArgumentException(INPUT_NOT_VALID);

    }

    public EventLog(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException(INPUT_NOT_VALID);
        }
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }


    public String getDescription() {
        return description;
    }

    public String viewInfo() {
        String reportDateTime = timestamp.format(formatter);

        return String.format("[%s] %s", reportDateTime, getDescription());
    }

}
