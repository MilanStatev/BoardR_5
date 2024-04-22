package com.boardr;

import java.time.LocalDate;

public class Issue extends BoardItem{
    private String description;

    public Issue(String title, String description, LocalDate dueDate) {
        super(title, dueDate);
    }
}
