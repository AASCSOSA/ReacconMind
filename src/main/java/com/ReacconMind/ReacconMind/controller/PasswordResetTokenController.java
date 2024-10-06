package com.ReacconMind.ReacconMind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ReacconMind.ReacconMind.service.PasswordResetTokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/password")
public class PasswordResetTokenController {
    @Autowired
    private PasswordResetTokenService passwordResetService;

    @Operation(summary = "Request password reset token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset token created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid email or user not found")
    })
    @PostMapping("/reset-request")
    public String resetPassword(@RequestParam String email) {
        if (email != null && !email.isEmpty()) {
        // Intentar crear un token de restablecimiento de contraseña
        boolean tokenCreado = passwordResetService.createPasswordResetToken(email);
        
        if (tokenCreado) {
            return "El token de restablecimiento de contraseña ha sido enviado a tu dirección de correo electrónico.";
        } else {
            return "No se encontró ningún usuario con la dirección de correo electrónico proporcionada.";
        }
    } else {
        return "La dirección de correo electrónico no puede ser nula o estar vacía.";
    }
    }

    @Operation(summary = "Validate password reset token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The token is valid"),
            @ApiResponse(responseCode = "400", description = "The token is invalid or has already been used")
    })
    @PostMapping("/validate-token")
    public String validateToken(@RequestParam String token) {
        boolean isValid = passwordResetService.validatePasswordResetToken(token);

        if (isValid) {
            return "The token is valid.";
        } else {
            return "The token is invalid or has already been used.";
        }
    }
}
