package com.utils;

import java.time.LocalDate;

public class ValidationHelper {
    public static void validateStringLength(String input, int minLength, int maxLength, String errorMessage){
        validateIntRange(minLength, maxLength, input.length(), errorMessage);
    }

    public static void validateIntRange(int minValue, int maxValue, int actualValue, String errorMessage) {
        if (actualValue < minValue || maxValue < actualValue ) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateDueDateIsFuture(LocalDate date, String errorMessage){
        if (date.isBefore(LocalDate.now()) ) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static <T> boolean areFieldCreated(T fieldName) {
        return fieldName != null;
    }

    public static boolean isStatusValid(String currentStatus, String stopStatus) {
        return !currentStatus.equals(stopStatus);
    }

    public static void validateInputIsNotEmpty(String input, String errorMessage){
        if (input == null || input.isEmpty()){
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
