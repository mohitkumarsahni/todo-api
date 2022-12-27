package com.sahni.todoapi.controllers;

import ch.qos.logback.classic.Logger;
import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.models.responses.TaskListResponse;
import com.sahni.todoapi.services.TaskListsService;
import org.slf4j.LoggerFactory;
import com.sahni.todoapi.models.requests.CreateTaskListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sahni.todoapi.constants.ToDoAPIConstants.*;

@RestController
public class TaskListsController {

    private static final Logger log = (Logger) LoggerFactory.getLogger(TaskListsController.class);

    @Autowired
    private TaskListsService taskListsService;

    @PostMapping(API + "/" + VERSION_1 + "/" + TASKLISTS_ENDPOINT)
    public TaskListResponse createTaskList(@RequestBody(required = true) CreateTaskListRequest createTaskListRequest) throws ToDoAppException {
        log.info("Incoming request for task list creation.");
        return taskListsService.createTaskList(createTaskListRequest);
    }
}
