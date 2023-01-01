package com.sahni.todoapi.validations.tasks;

import ch.qos.logback.classic.Logger;
import com.sahni.todoapi.exceptionhandle.ToDoAppErrors;
import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.models.TaskStatus;
import com.sahni.todoapi.models.requests.UpdateTaskListRequest;
import com.sahni.todoapi.models.requests.UpdateTaskRequest;
import com.sahni.todoapi.services.TasksService;
import com.sahni.todoapi.validations.Validator;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import static com.sahni.todoapi.constants.ToDoAPIConstants.MAXIMUM_LENGTH_FOR_DESCRIPTION;
import static com.sahni.todoapi.exceptionhandle.ToDoAppErrorMessages.*;
import static com.sahni.todoapi.exceptionhandle.ToDoAppErrorMessages.DESCRIPTION_MANDATORY_FIELD_MESSAGE;

@Component
public class UpdateTaskValidator implements Validator {

    private static final Logger log = (Logger) LoggerFactory.getLogger(UpdateTaskValidator.class);


    @Override
    public <T> void validate(T object) throws ToDoAppException {
        UpdateTaskRequest updateTaskRequest = (UpdateTaskRequest) object;
        validateMandatory(updateTaskRequest);
        validateUuid(updateTaskRequest.getUuid());
        validateLengths(updateTaskRequest.getDescription());
        validateStatus(updateTaskRequest.getStatus());
        log.info("Received request object for task update has been validated.");
    }

    private void validateStatus(String status) throws ToDoAppException {
        if (Objects.isNull(status)) {
            return;
        }
        TaskStatus taskStatus = Arrays.stream(TaskStatus.values())
                .filter(statusValue -> statusValue.name().equals(status))
                .findFirst()
                .orElse(null);
        if (Objects.isNull(taskStatus)) {
            throw new ToDoAppException(TASK_STATUS_VALUE_INVALID_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
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

    private void validateMandatory(UpdateTaskRequest updateTaskRequest) throws ToDoAppException {
        if (Objects.isNull(updateTaskRequest.getUuid()) || updateTaskRequest.getUuid().isBlank() || updateTaskRequest.getUuid().isEmpty()) {
            throw new ToDoAppException(UUID_MANDATORY_FIELD_MESSAGE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }

        if (Objects.isNull(updateTaskRequest.getDescription()) && Objects.isNull(updateTaskRequest.getStatus())) {

            throw new ToDoAppException(MISSING_FIELD_FOR_UPDATE, ToDoAppErrors.TO_DO_APP_ERROR_001);
        }
    }
}
