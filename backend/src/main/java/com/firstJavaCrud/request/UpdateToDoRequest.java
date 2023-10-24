package com.firstJavaCrud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateToDoRequest {

    @JsonProperty("task_id")
    private Long taskId;

    @JsonProperty("task_title")
    private String taskTitle;

    @JsonProperty("task_body")
    private String taskBody;
}
