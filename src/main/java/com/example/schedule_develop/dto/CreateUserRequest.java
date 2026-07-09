package com.example.schedule_develop.dto;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
}
