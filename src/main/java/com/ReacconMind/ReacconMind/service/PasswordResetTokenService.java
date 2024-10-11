package com.ReacconMind.ReacconMind.service;

import com.ReacconMind.ReacconMind.model.PasswordResetToken;
import com.ReacconMind.ReacconMind.model.User;
import com.ReacconMind.ReacconMind.repository.PasswordResetTokenRepository;
import com.ReacconMind.ReacconMind.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PasswordResetTokenService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository; // Repositorio de User

    @Autowired
    private PasswordResetTokenRepository tokenRepository; // Repositorio de PasswordResetToken

    @Autowired
    private UserService userService;

    public Optional<PasswordResetToken> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public Optional<User> getUserByToken(String token) {
        return tokenRepository
            .findByToken(token) // Busca el token
            .map(PasswordResetToken::getUser); // Extrae el usuario del token
    }

    public boolean createPasswordResetToken(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Genera un token
            String token = TokenGenerator.generateToken();
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUser(user);
            // Guarda el token en la base de datos
            tokenRepository.save(resetToken);

            // Enviar el correo electrónico
            emailService.sendEmail(
                user.getEmail(),
                "Password Reset Request",
                token
            );
            return true; // Token creado exitosamente
        } else {
            return false; // Usuario no encontrado
        }
    }

    public boolean validatePasswordResetToken(String token, String password) {
        Optional<PasswordResetToken> optionalToken =
            tokenRepository.findByToken(token);

        if (!optionalToken.isPresent()) {
            return false; // Token no encontrado
        }

        PasswordResetToken resetToken = optionalToken.get();

        // Verificar si el token ha sido usado
        if (resetToken.isUsed()) {
            return false; // Token ya usado
        }

        // Convertir LocalDateTime a java.sql.Date
        LocalDateTime now = LocalDateTime.now();
        java.sql.Date currentDate = java.sql.Date.valueOf(now.toLocalDate()); // Obtiene la fecha actual como java.sql.Date
        // }

        // Verificar si el token ha caducado
        if (resetToken.getExpirationDate().before(currentDate)) {
            return false; // Token caducado
        } else {
            // Restablecer la contraseña del usuario
            User user = resetToken.getUser();
            user.setPassword(password); // Cifrar la nueva contraseña
            userService.save(user);

            resetToken.setUsed(true);
            tokenRepository.save(resetToken);
            return true;
        }
    }
}
