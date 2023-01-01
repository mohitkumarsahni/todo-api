package com.sahni.todoapi.validations.tasks;

import com.sahni.todoapi.exceptionhandle.ToDoAppErrors;
import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.models.requests.CreateTaskRequest;
import com.sahni.todoapi.validations.Validator;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

import static com.sahni.todoapi.constants.ToDoAPIConstants.MAXIMUM_LENGTH_FOR_DESCRIPTION;
import static com.sahni.todoapi.constants.ToDoAPIConstants.MAXIMUM_LENGTH_FOR_NAME;
import static com.sahni.todoapi.exceptionhandle.ToDoAppErrorMessages.*;

@Component
public class CreateTaskValidator implements Validator {


    @Override
    public <T> void validate(T object) throws ToDoAppException {
        CreateTaskRequest createTaskRequest = (CreateTaskRequest) object;
        validateMandatory(createTaskRequest);
        validateLengths(createTaskRequest);
        validateUuid(createTaskRequest.getTaskListUuid());
    }

    private void validateUuid(String taskListUuid) throws ToDoAppException {
        try {
            UUID isValidUUID = UUID.fromString(taskListUuid);
        } catch (IllegalArgumentException  ex) {
            throw new ToDoAppException(UUID_INVALID_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
    }

    private void validateLengths(CreateTaskRequest createTaskRequest) throws ToDoAppException {
        if (createTaskRequest.getName().length() > MAXIMUM_LENGTH_FOR_NAME) {
            throw new ToDoAppException(NAME_MAX_LENGTH_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }

        if (Objects.nonNull(createTaskRequest.getDescription()) && createTaskRequest.getDescription().length() > MAXIMUM_LENGTH_FOR_DESCRIPTION) {
            throw new ToDoAppException(DESCRIPTION_MAX_LENGTH_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
    }

    private void validateMandatory(CreateTaskRequest createTaskRequest) throws ToDoAppException {
        if (Objects.isNull(createTaskRequest.getName()) || createTaskRequest.getName().isBlank() || createTaskRequest.getName().isEmpty()) {
            throw new ToDoAppException(NAME_MANDATORY_FIELD_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }

        if (Objects.isNull(createTaskRequest.getDescription())) {
            throw new ToDoAppException(DESCRIPTION_MANDATORY_FIELD_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }

        if (Objects.isNull(createTaskRequest.getTaskListUuid()) || createTaskRequest.getTaskListUuid().isBlank() || createTaskRequest.getTaskListUuid().isEmpty()) {
            throw new ToDoAppException(UUID_MANDATORY_FIELD_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
    }
}
