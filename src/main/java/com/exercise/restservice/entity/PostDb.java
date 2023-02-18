package com.exercise.restservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "posts")
public class PostDb {

    @Id
    private Long id;

    private Long userId;

    private String title;

    private String body;
}
