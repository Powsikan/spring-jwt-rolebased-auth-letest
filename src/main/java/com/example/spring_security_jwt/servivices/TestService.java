package com.example.spring_security_jwt.servivices;

import com.example.spring_security_jwt.config.jwt.JwtAuthenticationResponse;
import com.example.spring_security_jwt.config.jwt.JwtUtil;
import com.example.spring_security_jwt.dtos.LoginRequest;
import com.example.spring_security_jwt.dtos.SignupRequest;
import com.example.spring_security_jwt.models.User;
import com.example.spring_security_jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    @Autowired
    private AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> login(LoginRequest loginRequest) {
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

    public ResponseEntity<?> signup(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRoles("ROLE_"+signupRequest.getRoles().toUpperCase());
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
