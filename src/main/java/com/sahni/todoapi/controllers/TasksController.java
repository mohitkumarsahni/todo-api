package com.sahni.todoapi.controllers;

import ch.qos.logback.classic.Logger;
import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.models.requests.CreateTaskRequest;
import com.sahni.todoapi.models.requests.UpdateTaskRequest;
import com.sahni.todoapi.models.responses.TaskResponse;
import com.sahni.todoapi.services.TasksService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sahni.todoapi.constants.ToDoAPIConstants.*;

@RestController
public class TasksController {

    private static final Logger log = (Logger) LoggerFactory.getLogger(TasksController.class);

    @Autowired
    private TasksService tasksService;

    @PostMapping(API + "/" + VERSION_1 + "/" + TASKS_ENDPOINT)
    public TaskResponse createTask(@RequestBody(required = true) CreateTaskRequest createTaskRequest) throws ToDoAppException {
        try {
            log.info("Incoming request for task creation.");
            return tasksService.createTask(createTaskRequest);
        } finally {
            log.info("Processing for task creation request finished.");
        }
    }

    @PutMapping(API + "/" + VERSION_1 + "/" + TASKS_ENDPOINT)
    public TaskResponse updateTask(@RequestBody(required = true) UpdateTaskRequest updateTaskRequest) throws ToDoAppException {
        try {
            log.info("Incoming request for task update.");
            return tasksService.updateTask(updateTaskRequest);
        } finally {
            log.info("Processing for task update request finished.");
        }
    }

    @GetMapping(API + "/" + VERSION_1 + "/" + TASKS_ENDPOINT + "/" + "{task_uuid}")
    public TaskResponse fetchTask(@PathVariable(name = "task_uuid", required = true) String taskUuid) throws ToDoAppException {
        try {
            log.info("Incoming request for task fetch.");
            return tasksService.getTask(taskUuid);
        } finally {
            log.info("Processing for task fetch request finished.");
        }
    }

    @DeleteMapping(API + "/" + VERSION_1 + "/" + TASKS_ENDPOINT + "/" + "{task_uuid}")
    public void deleteTask(@PathVariable(name = "task_uuid", required = true) String taskUuid) throws ToDoAppException {
        try {
            log.info("Incoming request for task delete.");
            tasksService.deleteTask(taskUuid);
        } finally {
            log.info("Processing for task delete request finished.");
        }
    }

    @GetMapping(API + "/" + VERSION_1 + "/" + TASKS_ENDPOINT + "/" + TASKLIST_ENDPOINT + "/" + "{tasklist_uuid}")
    public List<TaskResponse> fetchTasks(@PathVariable(name = "tasklist_uuid", required = true) String taskListUuid) throws ToDoAppException {
        try {
            log.info("Incoming request for tasks fetch.");
            return tasksService.getTasks(taskListUuid);
        } finally {
            log.info("Processing for task fetch request finished.");
        }
    }
}
