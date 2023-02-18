package com.exercise.restservice.entity.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class Authority {

    private Long id;
    private String roleCode;
    private String roleDescription;
}
