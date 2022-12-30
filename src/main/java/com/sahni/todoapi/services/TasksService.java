package com.sahni.todoapi.services;

import ch.qos.logback.classic.Logger;
import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.models.TaskLists;
import com.sahni.todoapi.models.TaskStatus;
import com.sahni.todoapi.models.Tasks;
import com.sahni.todoapi.models.requests.CreateTaskRequest;
import com.sahni.todoapi.models.responses.TaskResponse;
import com.sahni.todoapi.repositories.TaskListsRepository;
import com.sahni.todoapi.repositories.TasksRepository;
import com.sahni.todoapi.validations.tasks.CreateTaskValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.sahni.todoapi.exceptionhandle.ToDoAppErrorMessages.TASK_LIST_NOT_FOUND_MESSAGE;
import static com.sahni.todoapi.exceptionhandle.ToDoAppErrors.TO_DO_APP_ERROR_002;

@Service
public class TasksService {

    private static final Logger log = (Logger) LoggerFactory.getLogger(TasksService.class);

    @Autowired
    private CreateTaskValidator createTaskValidator;

    @Autowired
    private TaskListsRepository taskListsRepository;

    @Autowired
    private TasksRepository tasksRepository;


    public TaskResponse createTask(CreateTaskRequest createTaskRequest) throws ToDoAppException {
        log.info("Processing request for task creation.");
        createTaskValidator.validate(createTaskRequest);
        Tasks task = createTaskInDB(createTaskRequest);
        //return response
        return TaskResponse.builder()
                .uuid(task.getUuid())
                .name(task.getName())
                .description(task.getDescription())
                .taskListUuid(task.getTaskList().getUuid())
                .status(task.getStatus())
                .build();
    }

    private Tasks createTaskInDB(CreateTaskRequest createTaskRequest) throws ToDoAppException {
        TaskLists taskList = fetchTaskList(UUID.fromString(createTaskRequest.getTaskListUuid()));
        Tasks task = Tasks.builder()
                .uuid(UUID.randomUUID())
                .name(createTaskRequest.getName())
                .description(createTaskRequest.getDescription())
                .taskList(taskList)
                .createdAt(new Date())
                .updatedAt(new Date())
                .status(TaskStatus.PENDING)
                .build();
        Tasks taskCreated = null;
        try {
            taskCreated = tasksRepository.save(task);
            log.info("Task has been created. Returning the newly created object.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return taskCreated;
    }

    private TaskLists fetchTaskList(UUID taskListUuid) throws ToDoAppException {
        Optional<TaskLists> taskListsOptional = taskListsRepository.findById(taskListUuid);
        if (taskListsOptional.isEmpty() || taskListsOptional.get().getIsDeleted()) {
            log.info("Task list with uuid : " + taskListUuid + " either does not exist or deleted. Sending error response.");
            throw new ToDoAppException(TASK_LIST_NOT_FOUND_MESSAGE, TO_DO_APP_ERROR_002);
        }
        return taskListsOptional.get();
    }


}
