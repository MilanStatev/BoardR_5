package com.boardr;

import com.utils.ValidationHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardItem {
    protected static final int MIN_INPUT_LENGTH = 5;
    protected static final int MAX_INPUT_LENGTH = 30;
    protected static final String INPUT_LENGTH_NOT_VALID = String.format("Please provide a title with length between %d and %d chars",
            MIN_INPUT_LENGTH,
            MAX_INPUT_LENGTH);
    public static final String DUE_DATE_IN_THE_PAST = "Please provide a date in the future";
    public static final String STATUS_UPDATED = "Status changed from %s to %s";
    public static final Status INITIAL_STATUS = Status.OPEN;
    public static final String FIRST_STATUS = "Open";
    public static final String LAST_STATUS = "Verified";
    public static final String DUE_DATE_CHANGED = "DueDate changed from %s to %s";
    public static final String TITLE_CHANGED = "Title changed from %s to %s";
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

        addEventToHistory(getEventTitle(TITLE_CHANGED, getTitle(), title), this.title);

        this.title = title;
    }

    protected void setDueDate(LocalDate dueDate) {
        ValidationHelper.validateDueDateIsFuture(dueDate, DUE_DATE_IN_THE_PAST);

        addEventToHistory(getEventTitle(DUE_DATE_CHANGED, getDueDate(), dueDate), this.dueDate);

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
        String currentStatus = getStatus().toString();
        String eventTitle = "Can't revert, already at Open";

        if (ValidationHelper.isStatusValid(currentStatus, FIRST_STATUS)) {
            setStatus(updateStatus(-1));
            eventTitle = getEventTitle(STATUS_UPDATED, currentStatus, getStatus().toString());
        }

        addEventToHistory(eventTitle, getStatus());
    }

    public void advanceStatus() {
        String currentStatus = getStatus().toString();
        String eventTitle = "Can't advance, already at Verified";

        if (ValidationHelper.isStatusValid(currentStatus, LAST_STATUS)) {
            setStatus(updateStatus(1));
            eventTitle = getEventTitle(STATUS_UPDATED, currentStatus, getStatus().toString());
        }

        addEventToHistory(eventTitle, getStatus());
    }

    protected Status updateStatus(int direction) {
        return Status.values()[getStatus().ordinal() + direction];
    }

    protected  <E> String getEventTitle(String title, E changedFrom, E changedTo) {
        return String.format(title, changedFrom, changedTo);
    }

    protected <E> void addEventToHistory(String eventTitle, E eventTriggered) {
        if (ValidationHelper.areFieldCreated(eventTriggered)) {
            eventLogs.add(new EventLog(eventTitle));
        }
    }

    public void displayHistory() {
        eventLogs.forEach(event -> System.out.println(event.viewInfo()));
    }

    public String viewInfo() {
        return String.format("'%s', [%s | %s]", getTitle(), getStatus().toString(), getDueDate());
    }

}
