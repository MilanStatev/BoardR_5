package com.boardr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardItem {
    protected static final int MIN_INPUT_LENGTH = 5;
    protected static final int MAX_INPUT_LENGTH = 30;
    protected static final String INPUT_LENGTH_NOT_VALID = String.format("Please provide a title with length between %d and %d chars",
                                                            MIN_INPUT_LENGTH,
                                                            MAX_INPUT_LENGTH);
    private String title;
    private LocalDate dueDate;
    private Status status;
    protected final List<EventLog> eventLogs = new ArrayList<>();

    public BoardItem(String title, LocalDate dueDate) {
        this(title,dueDate, Status.OPEN);

    }

    protected BoardItem(String title, LocalDate dueDate, Status status){
        setTitle(title);
        setDueDate(dueDate);
        setStatus(status);

        addEventToHistory("Item created " + this.viewInfo());
    }



    protected void setTitle(String title) {
        if (title.length() < MIN_INPUT_LENGTH || title.length() > MAX_INPUT_LENGTH) {
            throw new IllegalArgumentException(INPUT_LENGTH_NOT_VALID);
        }

        if (this.title != null) {
            String eventTitle = String.format("Title changed from %s to %s", getTitle(), title);
            addEventToHistory(eventTitle);
        }
        this.title = title;
    }

    protected void setDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Please provide a date in the future");
        }

        if (this.dueDate != null) {
            String eventTitle = String.format("DueDate changed from %s to %s", getDueDate(), dueDate);
            addEventToHistory(eventTitle);
        }

        this.dueDate = dueDate;
    }

    protected void setStatus(Status status) {
        this.status = status;
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
            eventTitle = String.format("com.boardr.Status changed from %s to %s", oldStatus, getStatus());
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
            eventTitle = String.format("com.boardr.Status changed from %s to %s", oldStatus, getStatus());
        }

        addEventToHistory(eventTitle);
    }

    protected void addEventToHistory(String eventTitle) {
        eventLogs.add(new EventLog(eventTitle));
    }

    public void displayHistory() {
        eventLogs.forEach(event -> System.out.println(event.viewInfo()));
    }

    public String viewInfo() {
        return String.format("'%s', [%s | %s]", getTitle(), getStatus().toString(), getDueDate());
    }

}
