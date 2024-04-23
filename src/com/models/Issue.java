package com.models;

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
    public String viewInfo() {
        return  String.format("Task: %s, Description: %s", super.viewInfo(), this.description);
    }
}
