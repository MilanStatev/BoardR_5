package com.boardr;

import java.time.LocalDate;

public class Task extends BoardItem{
    public static final String NOT_VALID_ASSIGNEE_NAME = "Assignee name must be valid";
    private String assignee;

    public Task(String title, String assignee, LocalDate dueDate) {
        super(title, dueDate, Status.TODO);
        this.setAssignee(assignee);
    }

    public void setAssignee(String assignee) {
        if (assignee == null || assignee.isEmpty()){
            throw new IllegalArgumentException(NOT_VALID_ASSIGNEE_NAME);
        }
        if (assignee.length() < MIN_INPUT_LENGTH || assignee.length() > MAX_INPUT_LENGTH) {
            throw new IllegalArgumentException(INPUT_LENGTH_NOT_VALID);
        }

        if (this.assignee != null) {
            String eventTitle = String.format("Assignee changed from %s to %s", getAssignee(), assignee);
            addEventToHistory(eventTitle);
        }

        this.assignee = assignee;
    }

    public String getAssignee() {
        return assignee;
    }
}
