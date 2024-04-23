package com.models;

import com.models.enums.Status;
import com.utils.ValidationHelper;

import java.time.LocalDate;

public class Issue extends BoardItem {

    private final String description;

    public Issue(String title, String description, LocalDate dueDate) {
        super(title, dueDate);
        this.description = ValidationHelper.validateInputIsNotEmpty(description)
                ? "No Description"
                : description;
    }

    @Override
    public void setDueDate(LocalDate dueDate) {
        super.setDueDate(dueDate);
    }

    @Override
    public void revertStatus() {
        String eventTitle = "Issue status already Open";

        if (ValidationHelper.isStatusValid(getStatus().toString(), INITIAL_STATUS.toString())) {
            setStatus(INITIAL_STATUS);
            eventTitle = "Issue status set to Open";
        }
        addEventToHistory(eventTitle, getStatus());
    }

    @Override
    public void advanceStatus() {
        String eventTitle = "Issue status already Verified";

        if (ValidationHelper.isStatusValid(getStatus().toString(), FINAL_STATUS.toString())) {
            setStatus(FINAL_STATUS);
            eventTitle = "Issue status set to Verified";
        }

        addEventToHistory(eventTitle, getStatus());
    }


//    @Override
//    public String viewInfo() {
//        return String.format("Task: %s, Description: %s", super.viewInfo(), this.description);
//    }

}
