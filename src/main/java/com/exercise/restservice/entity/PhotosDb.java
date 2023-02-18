package com.exercise.restservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "photos")
public class PhotosDb {


    @Id
    private Long id;

    private Long albumId;

    private String title;

    private String url;

    private String thumbnailUrl;
}
