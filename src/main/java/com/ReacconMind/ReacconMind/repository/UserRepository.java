package com.ReacconMind.ReacconMind.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ReacconMind.ReacconMind.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByEmail(String email); // Este método está bien

  User findByUserName(String userName); // Este método está bien

  Optional<User> findUserByEmail(String email);
}
