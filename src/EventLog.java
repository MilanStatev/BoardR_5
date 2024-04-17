import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLog {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
    private final String description;
    private final LocalDateTime timestamp;

    public EventLog() {
        throw new IllegalArgumentException("Description should not be empty");

    }

    public EventLog(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description should not be empty");
        }
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }


    public String getDescription() {
        return description;
    }

    public String viewInfo() {
        String reportDateTime = this.timestamp.format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss"));

        return String.format("[%s] %s", reportDateTime, getDescription());
    }

}
