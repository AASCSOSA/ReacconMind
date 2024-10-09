package com.ReacconMind.ReacconMind.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ReacconMind.ReacconMind.exception.UserAlreadyExistsException;
import com.ReacconMind.ReacconMind.model.AuthType;
import com.ReacconMind.ReacconMind.model.StatusType;
import com.ReacconMind.ReacconMind.model.User;
import com.ReacconMind.ReacconMind.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder; // Inyectar PasswordEncoder

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public boolean userExists(Integer idUser, String email, String userName) {
    return userRepository.existsById(idUser) ||
        userRepository.findByEmail(email) != null ||
        userRepository.findByUserName(userName) != null;
  }

  public List<User> getAllActive() {
    return userRepository.findAll().stream()
        .filter(user -> user.getStatus() == StatusType.Active)
        .collect(Collectors.toList());
  }

  public void save(User user) {
    // Cifrar la contraseña antes de guardar el usuario
    if (user.getTypeAuth() == AuthType.Google) {

      userRepository.save(user);
    } else {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);

    }
  }

  public User getByIdUser(Integer idUser) {
    return userRepository.findById(idUser)
        .orElseThrow(() -> new UserAlreadyExistsException("User not found with id: " + idUser));
  }

  public User update(User userDetails, Integer idUser) {
    User existingUser = userRepository.findById(idUser)
        .orElseThrow(() -> new UserAlreadyExistsException("User not found with id: " + idUser));

    existingUser.setName(userDetails.getName());
    existingUser.setEmail(userDetails.getEmail());

    // Si se actualiza la contraseña, también debe cifrarse
    if (!userDetails.getPassword().isEmpty()) {
      existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
    }

    existingUser.setImageProfile(userDetails.getImageProfile());
    existingUser.setImageFacade(userDetails.getImageFacade());
    existingUser.setBiography(userDetails.getBiography());
    existingUser.setUserName(userDetails.getUserName());
    existingUser.setTypeAuth(userDetails.getTypeAuth());

    return userRepository.save(existingUser);
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
}
