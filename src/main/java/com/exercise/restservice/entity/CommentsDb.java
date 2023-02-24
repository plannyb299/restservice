package com.exercise.restservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "comments")
public class CommentsDb {

    @Id
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}
