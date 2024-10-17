package com.reacconmind.reacconmind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reacconmind.reacconmind.model.User;
import com.reacconmind.reacconmind.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        User user = userRepository
            .findUserByEmail(username)
            .orElseThrow(() ->
                new UsernameNotFoundException(
                    "User not found with email: " + username
                )
            );
        return org.springframework.security.core.userdetails.User.withUsername(
            user.getEmail()
        )
            .password(user.getPassword())
            .roles("USER")
            .build();
    }
}
