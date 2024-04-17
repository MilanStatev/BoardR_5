import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardItem {
    private String title;
    private LocalDate dueDate;
    private Status status;

    private final List<EventLog> eventLogs = new ArrayList<>();

    public BoardItem(String title, LocalDate dueDate) {
        setTitle(title);
        setDueDate(dueDate);
        this.status = Status.OPEN;

        addEventToHistory("Item created " + this.viewInfo());
    }

    public void setTitle(String title) {
        if (title.length() < 5 || title.length() > 30) {
            throw new IllegalArgumentException("Please provide a title with length between 5 and 30 chars");
        }

        if (eventLogs.size() != 0) {
            String eventTitle = String.format("Title changed from %s to %s", getTitle(), title);
            addEventToHistory(eventTitle);
        }
        this.title = title;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Please provide a date in the future");
        }

        if (eventLogs.size() != 0) {
            String eventTitle = String.format("DueDate changed from %s to %s", getDueDate(), dueDate);
            addEventToHistory(eventTitle);
        }

        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void revertStatus() {
        int currentStatusValue = getStatus().ordinal();
        int newStatusValue = currentStatusValue - 1;

        Status oldStatus = getStatus();
        String eventTitle = "Can't revert, already at Open";

        if (newStatusValue >= 0) {
            this.status = Status.values()[newStatusValue];
            eventTitle = String.format("Status changed from %s to %s", oldStatus, getStatus());
        }

        addEventToHistory(eventTitle);
    }

    public void advanceStatus() {
        int currentStatusValue = getStatus().ordinal();
        int newStatusValue = currentStatusValue + 1;

        Status oldStatus = getStatus();
        String eventTitle = "Can't advance, already at Verified";

        if (newStatusValue < Status.values().length) {
            this.status = Status.values()[newStatusValue];
            eventTitle = String.format("Status changed from %s to %s", oldStatus, getStatus());
        }

        addEventToHistory(eventTitle);
    }

    private void addEventToHistory(String eventTitle) {
        eventLogs.add(new EventLog(eventTitle));
    }

    public String viewInfo() {
        return String.format("'%s', [%s | %s]", getTitle(), getStatus().toString(), getDueDate());
    }

    public void displayHistory() {
        eventLogs.forEach(event -> System.out.println(event.viewInfo()));
    }

}
