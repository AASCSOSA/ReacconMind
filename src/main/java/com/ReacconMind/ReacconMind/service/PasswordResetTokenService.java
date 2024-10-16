package com.ReacconMind.ReacconMind.service;

import com.ReacconMind.ReacconMind.model.PasswordResetToken;
import com.ReacconMind.ReacconMind.model.User;
import com.ReacconMind.ReacconMind.repository.PasswordResetTokenRepository;
import com.ReacconMind.ReacconMind.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PasswordResetTokenService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private UserService userService;

    public Optional<PasswordResetToken> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public Optional<User> getUserByToken(String token) {
        return tokenRepository
            .findByToken(token)
            .map(PasswordResetToken::getUser);
    }

    public String createPasswordResetToken(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return "User not found for email";
        }

        String token = TokenGenerator.generateToken();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);

        tokenRepository.save(resetToken);

        emailService.sendEmail(
            user.getEmail(),
            "Password Reset Request",
            token
        );

        return "Request processed";
    }

    public boolean validatePasswordResetToken(String token, String password) {
        Optional<PasswordResetToken> optionalToken =
            tokenRepository.findByToken(token);

        if (!optionalToken.isPresent()) {
            throw new RuntimeException("Token not found");
        }

        PasswordResetToken resetToken = optionalToken.get();

        if (resetToken.isUsed()) {
            throw new RuntimeException("Token has already been used");
        }

        LocalDateTime now = LocalDateTime.now();
        java.sql.Date currentDate = java.sql.Date.valueOf(now.toLocalDate());

        if (resetToken.getExpirationDate().before(currentDate)) {
            throw new RuntimeException("Token has expired");
        }

        User user = resetToken.getUser();
        user.setPassword(password);
        userService.save(user);

        resetToken.setUsed(true);
        tokenRepository.save(resetToken);
        return true;
    }
}
