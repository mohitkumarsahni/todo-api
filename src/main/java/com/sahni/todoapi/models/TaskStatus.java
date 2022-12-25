package com.sahni.todoapi.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TaskStatus {

    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private String status;

    public String getStatus() {
        return status;
    }
}
