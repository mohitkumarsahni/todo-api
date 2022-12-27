package com.sahni.todoapi.services;

import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.models.TaskLists;
import com.sahni.todoapi.models.requests.CreateTaskListRequest;
import com.sahni.todoapi.models.responses.TaskListResponse;
import com.sahni.todoapi.repositories.TaskListsRepository;
import com.sahni.todoapi.validations.TaskListsValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.sahni.todoapi.exceptionhandle.ToDoAppErrorMessages.DUPLICATE_ENTRY_FOUND;
import static com.sahni.todoapi.exceptionhandle.ToDoAppErrors.TO_DO_APP_ERROR_004;

@Service
@Slf4j
public class TaskListsService {

    @Autowired
    private TaskListsValidator taskListsValidator;

    @Autowired
    private TaskListsRepository taskListsRepository;

    public TaskListResponse createTaskList(CreateTaskListRequest createTaskListRequest) throws ToDoAppException {
        taskListsValidator.validate(createTaskListRequest);
        TaskLists taskLists = createTaskListsInDB(createTaskListRequest);
        return TaskListResponse.builder()
                .uuid(taskLists.getUuid())
                .name(taskLists.getName())
                .description(taskLists.getDescription())
                .build();
    }

    private TaskLists createTaskListsInDB(CreateTaskListRequest createTaskListRequest) throws ToDoAppException {
        checkExistence(createTaskListRequest.getName());
        TaskLists taskList = TaskLists.builder()
                .name(createTaskListRequest.getName())
                .description(createTaskListRequest.getDescription())
                .uuid(UUID.randomUUID())
                .build();
        taskList.setCreatedAt(new Date());
        taskList.setUpdatedAt(new Date());
        TaskLists taskLists = null;
        try {
            taskLists = taskListsRepository.save(taskList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return taskLists;
    }

    private void checkExistence(String name) throws ToDoAppException {
        Optional<TaskLists> taskListsOptional = taskListsRepository.findByName(name);
        if (taskListsOptional.isPresent() && !taskListsOptional.get().getIsDeleted()) {
            throw new ToDoAppException(DUPLICATE_ENTRY_FOUND + name, TO_DO_APP_ERROR_004);
        }
    }
}
