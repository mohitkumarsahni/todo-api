package com.sahni.todoapi.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskListRequest {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("description")
    private String description;
}
