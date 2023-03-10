package com.exercise.restservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Todos {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("completed")
    private String completed;
}
