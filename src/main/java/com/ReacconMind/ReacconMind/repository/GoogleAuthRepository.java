package com.ReacconMind.ReacconMind.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ReacconMind.ReacconMind.model.GoogleAuth;

public interface GoogleAuthRepository extends JpaRepository<GoogleAuth, Integer> {
  Optional<GoogleAuth> findByGoogleId(String googleId);
}
