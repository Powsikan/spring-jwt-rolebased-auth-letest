package com.example.spring_security_jwt.controllers;

import com.example.spring_security_jwt.dtos.LoginRequest;
import com.example.spring_security_jwt.dtos.SignupRequest;
import com.example.spring_security_jwt.servivices.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/home")
    public String home() {
        return "This is home page";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "This is dashboard page";
    }

    @GetMapping("/manage")
    public String manage() {
        return "This is manage page";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        return testService.signup(signupRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
       return testService.login(loginRequest);
    }

}

