package com.example.spring_security_jwt.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}

