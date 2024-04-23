package com.models.enums;

public enum Status {
    OPEN( "Open"),
    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    DONE("Done"),
    VERIFIED("Verified");

    private final String value;

    Status(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return this.value;
    }
}
