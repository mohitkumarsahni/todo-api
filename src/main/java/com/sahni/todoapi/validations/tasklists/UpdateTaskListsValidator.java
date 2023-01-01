package com.sahni.todoapi.validations.tasklists;

import ch.qos.logback.classic.Logger;
import com.sahni.todoapi.exceptionhandle.ToDoAppErrors;
import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.models.requests.UpdateTaskListRequest;
import com.sahni.todoapi.validations.Validator;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

import static com.sahni.todoapi.constants.ToDoAPIConstants.MAXIMUM_LENGTH_FOR_DESCRIPTION;
import static com.sahni.todoapi.exceptionhandle.ToDoAppErrorMessages.*;

@Component
public class UpdateTaskListsValidator implements Validator {

    private static final Logger log = (Logger) LoggerFactory.getLogger(UpdateTaskListsValidator.class);

    @Override
    public <T> void validate(T object) throws ToDoAppException {
        UpdateTaskListRequest updateTaskListRequest = (UpdateTaskListRequest) object;
        validateMandatory(updateTaskListRequest);
        validateUuid(updateTaskListRequest.getUuid());
        validateLengths(updateTaskListRequest.getDescription());
        log.info("Received request object for task list update has been validated.");
    }

    private void validateLengths(String description) throws ToDoAppException {
        if (Objects.nonNull(description) && description.length() > MAXIMUM_LENGTH_FOR_DESCRIPTION) {
            throw new ToDoAppException(DESCRIPTION_MAX_LENGTH_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
    }

    private void validateUuid(String uuid) throws ToDoAppException {
        try {
            UUID isValidUUID = UUID.fromString(uuid);
        } catch (IllegalArgumentException  ex) {
            throw new ToDoAppException(UUID_INVALID_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
    }

    private void validateMandatory(UpdateTaskListRequest updateTaskListRequest) throws ToDoAppException {
        if (Objects.isNull(updateTaskListRequest.getUuid()) || updateTaskListRequest.getUuid().isBlank() || updateTaskListRequest.getUuid().isEmpty()) {
            throw new ToDoAppException(UUID_MANDATORY_FIELD_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }

        if (Objects.isNull(updateTaskListRequest.getDescription())) {
            throw new ToDoAppException(DESCRIPTION_MANDATORY_FIELD_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
    }

}
