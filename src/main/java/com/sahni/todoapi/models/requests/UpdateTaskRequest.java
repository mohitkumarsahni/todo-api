package com.sahni.todoapi.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateTaskRequest {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;
}
