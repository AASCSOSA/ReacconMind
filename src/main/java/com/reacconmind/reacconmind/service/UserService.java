package com.reacconmind.reacconmind.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reacconmind.reacconmind.model.AuthType;
import com.reacconmind.reacconmind.model.StatusType;
import com.reacconmind.reacconmind.model.User;
import com.reacconmind.reacconmind.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean userExists(Integer idUser, String email, String userName) {
        return (userRepository.existsById(idUser) ||
                userRepository.findByEmail(email) != null ||
                userRepository.findByUserName(userName) != null);
    }

    public List<User> getAllActive() {
        return userRepository
                .findAll()
                .stream()
                .filter(user -> user.getStatus() == StatusType.Active)
                .collect(Collectors.toList());
    }

    public void save(User user) {
        if (user.getTypeAuth() == AuthType.Google) {
            userRepository.save(user);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    public User getByIdUser(Integer idUser) {
        return userRepository.findById(idUser).get();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<User> getAll(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<User> users = userRepository.findAll(pageRequest);
        return users.getContent();
    }
}
