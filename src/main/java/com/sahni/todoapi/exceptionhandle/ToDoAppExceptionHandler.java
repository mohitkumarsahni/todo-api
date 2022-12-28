package com.sahni.todoapi.exceptionhandle;

import com.sahni.todoapi.models.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.sahni.todoapi.exceptionhandle.ToDoAppErrors.*;

@RestControllerAdvice
public class ToDoAppExceptionHandler {

    @ExceptionHandler({ToDoAppException.class})
    public ResponseEntity<?> handleToDoAppException(ToDoAppException ex) {
        switch (ex.getErrorCode()) {
            case TO_DO_APP_ERROR_001:
                return new ResponseEntity<> (
                        ErrorResponse.builder()
                                .code(TO_DO_APP_ERROR_001.name())
                                .message(ex.getMessage())
                                .build(),
                        HttpStatus.UNPROCESSABLE_ENTITY
                );
            case TO_DO_APP_ERROR_002:
                return new ResponseEntity<> (
                        ErrorResponse.builder()
                                .code(TO_DO_APP_ERROR_002.name())
                                .message(ex.getMessage())
                                .build(),
                        HttpStatus.NOT_FOUND
                );
            case TO_DO_APP_ERROR_003:
                return new ResponseEntity<> (
                        ErrorResponse.builder()
                                .code(TO_DO_APP_ERROR_003.name())
                                .message(ex.getMessage())
                                .build(),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            case TO_DO_APP_ERROR_004:
                return new ResponseEntity<> (
                        ErrorResponse.builder()
                                .code(TO_DO_APP_ERROR_004.name())
                                .message(ex.getMessage())
                                .build(),
                        HttpStatus.CONFLICT
                );
        }

        return new ResponseEntity<> (
                ErrorResponse.builder()
                        .code(TO_DO_APP_ERROR_003.name())
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
