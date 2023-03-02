package com.example.spring_security_jwt.config.jwt;

public class JwtAuthenticationResponse {

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

