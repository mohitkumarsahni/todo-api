package com.sahni.todoapi.services;

import ch.qos.logback.classic.Logger;
import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.models.TaskLists;
import com.sahni.todoapi.models.TaskStatus;
import com.sahni.todoapi.models.Tasks;
import com.sahni.todoapi.models.requests.CreateTaskRequest;
import com.sahni.todoapi.models.requests.UpdateTaskRequest;
import com.sahni.todoapi.models.responses.TaskResponse;
import com.sahni.todoapi.repositories.TaskListsRepository;
import com.sahni.todoapi.repositories.TasksRepository;
import com.sahni.todoapi.validations.tasks.CreateTaskValidator;
import com.sahni.todoapi.validations.tasks.GetTaskValidator;
import com.sahni.todoapi.validations.tasks.UpdateTaskValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.sahni.todoapi.exceptionhandle.ToDoAppErrorMessages.TASK_LIST_NOT_FOUND_MESSAGE;
import static com.sahni.todoapi.exceptionhandle.ToDoAppErrorMessages.TASK_NOT_FOUND_MESSAGE;
import static com.sahni.todoapi.exceptionhandle.ToDoAppErrors.TO_DO_APP_ERROR_002;

@Service
public class TasksService {

    private static final Logger log = (Logger) LoggerFactory.getLogger(TasksService.class);

    @Autowired
    private CreateTaskValidator createTaskValidator;

    @Autowired
    private GetTaskValidator getTaskValidator;

    @Autowired
    private UpdateTaskValidator updateTaskValidator;

    @Autowired
    private TaskListsRepository taskListsRepository;

    @Autowired
    private TasksRepository tasksRepository;


    public TaskResponse createTask(CreateTaskRequest createTaskRequest) throws ToDoAppException {
        log.info("Processing request for task creation.");
        createTaskValidator.validate(createTaskRequest);
        Tasks task = createTaskInDB(createTaskRequest);
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


    public TaskResponse getTask(String taskUuid) throws ToDoAppException {
        log.info("Processing request for task fetch.");
        getTaskValidator.validate(taskUuid);
        Tasks task = fetchTask(taskUuid);
        return TaskResponse.builder()
                .uuid(task.getUuid())
                .name(task.getName())
                .description(task.getDescription())
                .taskListUuid(task.getTaskList().getUuid())
                .status(task.getStatus())
                .build();
    }

    private Tasks fetchTask(String taskUuid) throws ToDoAppException {
        Optional<Tasks> tasksOptional = Optional.empty();
        try {
            tasksOptional = tasksRepository.findById(UUID.fromString(taskUuid));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (tasksOptional.isEmpty() || tasksOptional.get().getIsDeleted()) {
            log.info("Task list with uuid : " + taskUuid + " either does not exist or deleted. Sending error response.");
            throw new ToDoAppException(TASK_NOT_FOUND_MESSAGE, TO_DO_APP_ERROR_002);
        }
        return tasksOptional.get();
    }


    public void deleteTask(String taskUuid) throws ToDoAppException {
        log.info("Processing request for task delete.");
        getTaskValidator.validate(taskUuid);
        Tasks task = checkTaskExistence(UUID.fromString(taskUuid));
        deleteTaskListInDB(task);
    }

    private void deleteTaskListInDB(Tasks task) {
        try {
            task.setIsDeleted(true);
            task.setUpdatedAt(new Date());
            tasksRepository.save(task);
            log.info("Task has been deleted.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Tasks checkTaskExistence(UUID taskUuid) throws ToDoAppException {
        Optional<Tasks> tasksOptional = tasksRepository.findById(taskUuid);
        if (tasksOptional.isEmpty() || tasksOptional.get().getIsDeleted()) {
            log.info("Task with uuid : " + taskUuid + " either does not exist or deleted. Sending error response.");
            throw new ToDoAppException(TASK_NOT_FOUND_MESSAGE, TO_DO_APP_ERROR_002);
        }
        return tasksOptional.get();
    }


    public List<TaskResponse> getTasks(String taskListUuid) throws ToDoAppException {
        log.info("Processing request for tasks fetch.");
        getTaskValidator.validate(taskListUuid);
        List<Tasks> tasks = fetchTasks(taskListUuid);
        if (tasks.isEmpty()) {
            return new ArrayList<>();
        }
        List<TaskResponse> taskResponseList = new ArrayList<>();
        tasks.forEach(task -> {
            taskResponseList.add(TaskResponse.builder()
                    .uuid(task.getUuid())
                    .name(task.getName())
                    .description(task.getDescription())
                    .taskListUuid(task.getTaskList().getUuid())
                    .status(task.getStatus())
                    .build());
        });
        return taskResponseList;
    }

    private List<Tasks> fetchTasks(String taskListUuid) throws ToDoAppException {
        TaskLists taskList = checkTaskListExistence(UUID.fromString(taskListUuid));
        List<Tasks> tasks = null;
        try {
            tasks = tasksRepository.findByTaskListUuidAndIsDeleted(taskList.getUuid(), false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (Objects.isNull(tasks)) {
            return new ArrayList<>();
        }
        return tasks;
    }

    private TaskLists checkTaskListExistence(UUID taskListUuid) throws ToDoAppException {
        Optional<TaskLists> taskListsOptional = taskListsRepository.findById(taskListUuid);
        if (taskListsOptional.isEmpty() || taskListsOptional.get().getIsDeleted()) {
            log.info("Task list with uuid : " + taskListUuid + " either does not exist or deleted. Sending error response.");
            throw new ToDoAppException(TASK_LIST_NOT_FOUND_MESSAGE, TO_DO_APP_ERROR_002);
        }
        return taskListsOptional.get();
    }

    public TaskResponse updateTask(UpdateTaskRequest updateTaskRequest) throws ToDoAppException {
        updateTaskValidator.validate(updateTaskRequest);
        Tasks newTask = updateTaskInDB(updateTaskRequest);
        return TaskResponse.builder()
                .uuid(newTask.getUuid())
                .name(newTask.getName())
                .taskListUuid(newTask.getTaskList().getUuid())
                .status(newTask.getStatus())
                .description(newTask.getDescription())
                .build();
    }

    private Tasks updateTaskInDB(UpdateTaskRequest updateTaskRequest) throws ToDoAppException {
        Tasks task = checkTaskExistence(UUID.fromString(updateTaskRequest.getUuid()));
        if (Objects.nonNull(updateTaskRequest.getDescription())) {
            task.setDescription(updateTaskRequest.getDescription());
        }
        if (Objects.nonNull(updateTaskRequest.getStatus())) {
            Optional<TaskStatus> status = Arrays.stream(TaskStatus.values()).filter(taskStatus -> taskStatus.name().equals(updateTaskRequest.getStatus())).findFirst();
            task.setStatus(status.get());
        }
        task.setUpdatedAt(new Date());
        Tasks newTask = null;
        try {
            newTask = tasksRepository.save(task);
            log.info("Task has been updated. Returning the updated object.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newTask;
    }
}
