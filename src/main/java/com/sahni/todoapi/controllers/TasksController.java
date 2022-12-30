package com.sahni.todoapi.controllers;

import ch.qos.logback.classic.Logger;
import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.models.requests.CreateTaskRequest;
import com.sahni.todoapi.models.responses.TaskResponse;
import com.sahni.todoapi.services.TasksService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
