package com.sahni.todoapi.exceptionhandle;

public enum ToDoAppErrors {

    TO_DO_APP_ERROR_001("Invalid value"),
    TO_DO_APP_ERROR_002("Resource not found"),
    TO_DO_APP_ERROR_003("Internal server error"),
    TO_DO_APP_ERROR_004("Duplicate found");

    private final String message;

    ToDoAppErrors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
