package com.exercise.restservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Albums {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

}