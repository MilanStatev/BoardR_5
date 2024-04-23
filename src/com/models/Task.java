package com.models;

import com.models.enums.Status;
import com.utils.ValidationHelper;

import java.time.LocalDate;

public class Task extends BoardItem {
    public static final String NOT_VALID_ASSIGNEE_NAME = "Assignee name must be valid";
    public static final String ASSIGNEE_NAME_CHANGED = "Assignee changed from %s to %s";
    private String assignee;

    public Task(String title, String assignee, LocalDate dueDate) {
        super(title, dueDate, Status.TODO);
        setAssignee(assignee);
    }

    public void setAssignee(String assignee) {
        ValidationHelper.validateInputIsNotEmpty(assignee, NOT_VALID_ASSIGNEE_NAME);
        ValidationHelper.validateStringLength(assignee, MIN_INPUT_LENGTH, MAX_INPUT_LENGTH, INPUT_LENGTH_NOT_VALID);

        addEventToHistory(getEventTitle(ASSIGNEE_NAME_CHANGED, getAssignee(), assignee), this.assignee);

        this.assignee = assignee;
    }

    public String getAssignee() {
        return assignee;
    }

    @Override
    public String viewInfo() {
        return  String.format("Task: %s, Assignee: %s", super.viewInfo(), this.getAssignee());
    }
}
