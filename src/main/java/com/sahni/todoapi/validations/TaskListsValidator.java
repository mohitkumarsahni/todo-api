package com.sahni.todoapi.validations;

import com.sahni.todoapi.exceptionhandle.ToDoAppErrors;
import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.models.requests.CreateTaskListRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.sahni.todoapi.constants.ToDoAPIConstants.MAXIMUM_LENGTH_FOR_DESCRIPTION;
import static com.sahni.todoapi.constants.ToDoAPIConstants.MAXIMUM_LENGTH_FOR_NAME;
import static com.sahni.todoapi.exceptionhandle.ToDoAppErrorMessages.*;

@Component
public class TaskListsValidator implements Validator {
    @Override
    public <T> void validate(T object) throws ToDoAppException {
        CreateTaskListRequest createTaskListRequest = (CreateTaskListRequest) object;
        validateMandatory(createTaskListRequest.getName());
        validateLengths(createTaskListRequest);
    }

    private void validateLengths(CreateTaskListRequest createTaskListRequest) throws ToDoAppException{
        if (createTaskListRequest.getName().length() > MAXIMUM_LENGTH_FOR_NAME) {
            throw new ToDoAppException(NAME_MAX_LENGTH_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }

        if (Objects.nonNull(createTaskListRequest.getDescription()) && createTaskListRequest.getDescription().length() > MAXIMUM_LENGTH_FOR_DESCRIPTION) {
            throw new ToDoAppException(DESCRIPTION_MAX_LENGTH_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
    }

    private void validateMandatory(String name) throws ToDoAppException {
        if (Objects.isNull(name) || name.isBlank() || name.isEmpty()) {
            throw new ToDoAppException(NAME_MANDATORY_FIELD_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
    }
}
