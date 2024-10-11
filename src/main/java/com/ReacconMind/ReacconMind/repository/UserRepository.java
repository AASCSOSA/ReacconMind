package com.ReacconMind.ReacconMind.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ReacconMind.ReacconMind.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByEmail(String email);

  User findByUserName(String userName);

  Optional<User> findUserByEmail(String email);
}
