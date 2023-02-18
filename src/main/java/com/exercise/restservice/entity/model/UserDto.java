package com.exercise.restservice.entity.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDto {

    private String firstName;

    private String lastName;

    private String userName;

    private boolean enabled=true;
    private Authority authority;
    private String password;

}
