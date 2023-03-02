package com.example.spring_security_jwt.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Data
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

