package com.ReacconMind.ReacconMind.service;

import com.ReacconMind.ReacconMind.model.GoogleAuth;
import com.ReacconMind.ReacconMind.repository.GoogleAuthRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GoogleAuthService {

    @Autowired
    private GoogleAuthRepository googleAuthRepository;

    public void save(GoogleAuth userGoogle) {
        googleAuthRepository.save(userGoogle);
    }

    public Optional<GoogleAuth> findByGoogleId(String googleId) {
        return googleAuthRepository.findByGoogleId(googleId);
    }
}