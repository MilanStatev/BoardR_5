import java.time.LocalDate;
import java.util.List;

public class BoardItem {
    private String title;
    private LocalDate dueDate;
    private Status status;

    private List<EventLog> eventLogs;

    public BoardItem(String title, LocalDate dueDate){
        setTitle(title);
        setDueDate(dueDate);
        this.status = Status.OPEN;

    }
    public void setTitle(String title) {
        if (title.length() < 5 || title.length() > 30) {
            throw new IllegalArgumentException("Please provide a title with length between 5 and 30 chars");
        }
        this.title = title;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Please provide a date in the future");
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

    public void revertStatus(){
        int currentStatusValue = getStatus().ordinal();

        if (currentStatusValue != 0) {
            for (Status value : Status.values()) {
                if (value.ordinal() == currentStatusValue - 1) {
                    this.status = value;
                    break;
                }
            }
        }
    }

    public void advanceStatus(){
        int currentStatusValue = getStatus().ordinal();

        if (currentStatusValue != Status.values().length - 1) {
            for (Status value : Status.values()) {
                if (value.ordinal() == currentStatusValue + 1) {
                    this.status = value;
                    break;
                }
            }
        }
    }

    public String viewInfo() {
        return String.format("'%s', [%s | %s]", getTitle(), getStatus().toString(), getDueDate() );
    }

}
