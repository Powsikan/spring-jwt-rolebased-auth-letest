package com.example.spring_security_jwt.dtos;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String roles;
}
