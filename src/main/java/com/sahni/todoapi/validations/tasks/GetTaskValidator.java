package com.sahni.todoapi.validations.tasks;

import com.sahni.todoapi.exceptionhandle.ToDoAppErrors;
import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.validations.Validator;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

import static com.sahni.todoapi.exceptionhandle.ToDoAppErrorMessages.UUID_INVALID_MESSAGE;
import static com.sahni.todoapi.exceptionhandle.ToDoAppErrorMessages.UUID_MANDATORY_FIELD_MESSAGE;

@Component
public class GetTaskValidator implements Validator {

    @Override
    public <T> void validate(T object) throws ToDoAppException {
        String taskUuid = (String) object;
        validateMandatory(taskUuid);
        validateUuid(taskUuid);
    }

    private void validateMandatory(String taskUuid) throws ToDoAppException {
        if (Objects.isNull(taskUuid) || taskUuid.isBlank() || taskUuid.isEmpty()) {
            throw new ToDoAppException(UUID_MANDATORY_FIELD_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
    }

    private void validateUuid(String uuid) throws ToDoAppException {
        try {
            UUID isValidUUID = UUID.fromString(uuid);
        } catch (IllegalArgumentException  ex) {
            throw new ToDoAppException(UUID_INVALID_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
    }
}
