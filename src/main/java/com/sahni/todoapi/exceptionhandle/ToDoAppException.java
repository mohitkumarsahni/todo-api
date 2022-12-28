package com.sahni.todoapi.exceptionhandle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ToDoAppException extends Exception {

    private ToDoAppErrors errorCode;
    private String message;

    public ToDoAppException(ToDoAppErrors errorCode) {
        this.errorCode = errorCode;
    }

    public ToDoAppException(String message, ToDoAppErrors errorCode) {
        this.errorCode = errorCode;
        this.message = message;
    }

}
