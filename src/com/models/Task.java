package com.models;

import com.models.enums.Status;
import com.utils.ValidationHelper;

import java.time.LocalDate;

public class Task extends BoardItem {
    public static final String NOT_VALID_ASSIGNEE_NAME = "Assignee name must be valid";

    private String assignee;

    public Task(String title, String assignee, LocalDate dueDate) {
        super(title, dueDate, Status.TODO);
        setAssignee(assignee);
    }

    public void setAssignee(String assignee) {
        ValidationHelper.validateInputIsNotEmpty(assignee, NOT_VALID_ASSIGNEE_NAME);
        ValidationHelper.validateStringLength(assignee, MIN_INPUT_LENGTH, MAX_INPUT_LENGTH, INPUT_LENGTH_NOT_VALID);

        addEventToHistory(getEventTitle(LOG_EVENT_UPDATE, getAssignee(), assignee, "Assignee"), this.assignee);

        this.assignee = assignee;
    }

    public String getAssignee() {
        return assignee;
    }


    @Override
    public void revertStatus() {
        String currentStatus = getStatus().toString();
        String eventTitle = "Can't revert, already at To Do";

        if (ValidationHelper.isStatusValid(currentStatus, Status.TODO.toString())) {
            setStatus(updateStatus(-1));
            eventTitle = getEventTitle(LOG_EVENT_UPDATE, currentStatus, getStatus().toString(), "Task status");
        }

        addEventToHistory(eventTitle, getStatus());

    }

    @Override
    public void advanceStatus() {
        String currentStatus = getStatus().toString();
        String eventTitle = "Can't advance, already at Verified";

        if (ValidationHelper.isStatusValid(currentStatus, FINAL_STATUS.toString())) {
            setStatus(updateStatus(1));
            eventTitle = getEventTitle(LOG_EVENT_UPDATE, currentStatus, getStatus().toString(), "Task status");
        }

        addEventToHistory(eventTitle, getStatus());
    }

//    @Override
//    public String viewInfo() {
//        return String.format("Task: %s, Assignee: %s", super.viewInfo(), this.getAssignee());
//    }
}
