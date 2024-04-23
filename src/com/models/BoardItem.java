package com.models;

import com.models.contracts.Printable;
import com.models.enums.Status;
import com.utils.ValidationHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class BoardItem implements Printable {
    protected static final int MIN_INPUT_LENGTH = 5;
    protected static final int MAX_INPUT_LENGTH = 30;
    protected static final String INPUT_LENGTH_NOT_VALID = String.format("Please provide a title with length between %d and %d chars",
            MIN_INPUT_LENGTH,
            MAX_INPUT_LENGTH);
    public static final String DUE_DATE_IN_THE_PAST = "Please provide a date in the future";
    protected static final String LOG_EVENT_UPDATE = "%s changed from %s to %s";
    protected static final Status INITIAL_STATUS = Status.OPEN;
    protected static final Status FINAL_STATUS = Status.VERIFIED;
    private String title;
    private LocalDate dueDate;
    private Status status;
    protected final List<EventLog> eventLogs = new ArrayList<>();

    public BoardItem(String title, LocalDate dueDate) {
        this(title, dueDate, INITIAL_STATUS);

    }

    protected BoardItem(String title, LocalDate dueDate, Status status) {
        setTitle(title);
        setDueDate(dueDate);
        setStatus(status);

        addEventToHistory(String.format("Item created %s", viewInfo()), this);
    }


    protected void setTitle(String title) {
        ValidationHelper.validateStringLength(title, MIN_INPUT_LENGTH, MAX_INPUT_LENGTH, INPUT_LENGTH_NOT_VALID);

        addEventToHistory(getEventTitle(LOG_EVENT_UPDATE, getTitle(), title, "Title"), this.title);

        this.title = title;
    }

    protected void setDueDate(LocalDate dueDate) {
        ValidationHelper.validateDueDateIsFuture(dueDate, DUE_DATE_IN_THE_PAST);

        addEventToHistory(getEventTitle(LOG_EVENT_UPDATE, getDueDate(), dueDate, "DueDate"), this.dueDate);

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


    public abstract void revertStatus();

    public abstract void advanceStatus();


    protected Status updateStatus(int direction) {
        return Status.values()[getStatus().ordinal() + direction];
    }

    protected  <E> String getEventTitle(String title, E changedFrom, E changedTo, String propertyName) {
        return String.format(title, propertyName, changedFrom, changedTo);
    }

    protected <E> void addEventToHistory(String eventTitle, E eventTriggered) {
        if (ValidationHelper.areFieldCreated(eventTriggered)) {
            eventLogs.add(new EventLog(eventTitle));
        }
    }

    public void displayHistory() {
        StringBuilder sb = new StringBuilder();
        eventLogs.forEach(event -> sb.append(event.viewInfo()).append(System.lineSeparator()));

        System.out.println(sb.toString().trim());
    }

    public String viewInfo() {
        return String.format("'%s', [%s | %s]", getTitle(), getStatus().toString(), getDueDate());
    }

}
