package com.ReacconMind.ReacconMind.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ReacconMind.ReacconMind.model.PasswordResetToken;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
  Optional<PasswordResetToken> findByToken(String token);

}
