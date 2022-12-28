package com.sahni.todoapi.validations;

import com.sahni.todoapi.exceptionhandle.ToDoAppException;

public interface Validator {
    public <T> void validate(T object) throws ToDoAppException;
}
