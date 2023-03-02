package com.example.spring_security_jwt.controllers;

import com.example.spring_security_jwt.config.jwt.JwtAuthenticationResponse;
import com.example.spring_security_jwt.config.jwt.JwtUtil;
import com.example.spring_security_jwt.dtos.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Validate the user's credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        if (authentication.isAuthenticated()) {
            // Generate JWT token
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            String token = jwtUtil.generate(userDetails);
            // Return the token in a response entity
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } else {
            return ResponseEntity.badRequest().body(new UsernameNotFoundException("Invalid username/password"));
        }
    }

}

