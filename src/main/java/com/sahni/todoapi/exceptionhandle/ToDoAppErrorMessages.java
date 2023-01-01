package com.sahni.todoapi.exceptionhandle;

public class ToDoAppErrorMessages {
    public static final String NAME_MANDATORY_FIELD_MESSAGE = "Name can not be null or blank or empty. And should be of 64 characters.";
    public static final String NAME_MAX_LENGTH_MESSAGE = "Allowed maximum length for name field is 64 characters.";
    public static final String DESCRIPTION_MAX_LENGTH_MESSAGE = "Allowed maximum length for description field is 256 characters.";
    public static final String DUPLICATE_ENTRY_FOUND = "Duplicate entry found for name: ";
    public static final String UUID_MANDATORY_FIELD_MESSAGE = "UUID can not be null or blank or empty. And should be a valid UUID of 36 characters.";
    public static final String DESCRIPTION_MANDATORY_FIELD_MESSAGE = "Description can not be null. And should be of 256 characters.";
    public static final String UUID_INVALID_MESSAGE = "Provided UUID is not a valid UUID. It should be a valid UUID of 36 characters.";
    public static final String TASK_LIST_NOT_FOUND_MESSAGE = "Task List with provided uuid either does not exist or has been deleted.";
    public static final String TASK_NOT_FOUND_MESSAGE = "Task with provided uuid either does not exist or has been deleted.";
    public static final String MISSING_FIELD_FOR_UPDATE = "Either task description or task status or both can be updated. Please provide a value to update.";
    public static final String TASK_STATUS_VALUE_INVALID_MESSAGE = "Provided value for task status is invalid. Allowed values are COMPLETED, IN_PROGRESS or PENDING.";
}
