import java.time.LocalDate;

public class BoardItem {
    private String title;
    private LocalDate dueDate;
    private Status status;

    public BoardItem(String title, LocalDate dueDate){
        this.title = title;
        this.dueDate = dueDate;
        this.status = Status.OPEN;
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
