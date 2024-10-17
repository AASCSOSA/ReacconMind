package com.reacconmind.reacconmind.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reacconmind.reacconmind.model.GoogleAuth;
import com.reacconmind.reacconmind.repository.GoogleAuthRepository;

import jakarta.transaction.Transactional;

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
