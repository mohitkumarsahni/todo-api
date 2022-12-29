package com.sahni.todoapi.controllers;

import ch.qos.logback.classic.Logger;
import com.sahni.todoapi.exceptionhandle.ToDoAppException;
import com.sahni.todoapi.models.requests.UpdateTaskListRequest;
import com.sahni.todoapi.models.responses.TaskListResponse;
import com.sahni.todoapi.services.TaskListsService;
import org.slf4j.LoggerFactory;
import com.sahni.todoapi.models.requests.CreateTaskListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.sahni.todoapi.constants.ToDoAPIConstants.*;

@RestController
public class TaskListsController {

    private static final Logger log = (Logger) LoggerFactory.getLogger(TaskListsController.class);

    @Autowired
    private TaskListsService taskListsService;

    @PostMapping(API + "/" + VERSION_1 + "/" + TASKLISTS_ENDPOINT)
    public TaskListResponse createTaskList(@RequestBody(required = true) CreateTaskListRequest createTaskListRequest) throws ToDoAppException {
        try {
            log.info("Incoming request for task list creation.");
            return taskListsService.createTaskList(createTaskListRequest);
        } finally {
            log.info("Processing for task list creation request finished.");
        }
    }

    @PutMapping(API + "/" + VERSION_1 + "/" + TASKLISTS_ENDPOINT)
    public TaskListResponse updateTaskList(@RequestBody(required = true) UpdateTaskListRequest updateTaskListRequest) throws ToDoAppException {
        try {
            log.info("Incoming request for task list update.");
            return taskListsService.updateTaskList(updateTaskListRequest);
        } finally {
            log.info("Processing for task list update request finished.");
        }
    }

    @GetMapping(API + "/" + VERSION_1 + "/" + TASKLISTS_ENDPOINT + "/" + "{task_list_uuid}")
    public TaskListResponse getTaskList(@PathVariable(name = "task_list_uuid", required = true) String taskListUuid) throws ToDoAppException {
        try {
            log.info("Incoming request for task list fetch.");
            return taskListsService.getTaskList(taskListUuid);
        } finally {
            log.info("Processing for task list get request finished.");
        }
    }

    @DeleteMapping(API + "/" + VERSION_1 + "/" + TASKLISTS_ENDPOINT + "/" + "{task_list_uuid}")
    public void deleteTaskList(@PathVariable(name = "task_list_uuid", required = true) String taskListUuid) throws ToDoAppException {
        try {
            log.info("Incoming request for task list delete.");
            taskListsService.deleteTaskList(taskListUuid);
        } finally {
            log.info("Processing for task list delete request finished.");
        }
    }
}
