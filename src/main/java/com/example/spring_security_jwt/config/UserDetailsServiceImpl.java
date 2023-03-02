package com.example.spring_security_jwt.config;


import com.example.spring_security_jwt.models.User;
import com.example.spring_security_jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findByUsername(username);

        user.orElseThrow(()->new UsernameNotFoundException("Not found:"+username) );

        return user.map(AuthUserDetails::new).get();

    }

}
