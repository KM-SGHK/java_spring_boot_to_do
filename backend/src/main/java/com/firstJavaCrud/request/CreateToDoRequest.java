package com.firstJavaCrud.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateToDoRequest {

    @JsonProperty("task_title") // must do
    private String taskTitle;

    @JsonProperty("task_body")
    private String taskBody;
}
