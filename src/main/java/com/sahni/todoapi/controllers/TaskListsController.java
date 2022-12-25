package com.sahni.todoapi.controllers;

import com.sahni.todoapi.models.requests.CreateTaskListRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sahni.todoapi.constants.ToDoAPIConstants.*;

@RestController
@Slf4j
public class TaskListsController {

    @PostMapping(API + "/" + VERSION_1 + "/" + TASKLISTS_ENDPOINT)
    public ResponseEntity<?> createTaskList(@RequestBody(required = true) CreateTaskListRequest createTaskListRequest) {
        log.info("Incoming request for task list creation.");
        return null;
    }
}
