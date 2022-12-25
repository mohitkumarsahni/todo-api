package com.sahni.todoapi.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskListRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;
}
