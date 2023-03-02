package com.example.spring_security_jwt.repository;

import com.example.spring_security_jwt.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {


    Optional<User> findByUsername(String username);
}
