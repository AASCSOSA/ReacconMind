package com.reacconmind.reacconmind.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reacconmind.reacconmind.model.GoogleAuth;


public interface GoogleAuthRepository extends JpaRepository<GoogleAuth, Integer> {
  Optional<GoogleAuth> findByGoogleId(String googleId);
}
