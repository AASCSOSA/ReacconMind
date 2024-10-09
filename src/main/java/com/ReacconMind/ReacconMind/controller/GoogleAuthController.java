package com.ReacconMind.ReacconMind.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReacconMind.ReacconMind.model.AuthType;
import com.ReacconMind.ReacconMind.model.GoogleAuth;
import com.ReacconMind.ReacconMind.model.User;
import com.ReacconMind.ReacconMind.service.GoogleAuthService;
import com.ReacconMind.ReacconMind.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api") // Puedes agregar un prefijo para mejorar la organización
public class GoogleAuthController {
  private final UserService userService;
  private final GoogleAuthService googleAuthService;

  // Inyectamos UserService y GoogleAuthService
  public GoogleAuthController(UserService userService, GoogleAuthService googleAuthService) {
    this.userService = userService;
    this.googleAuthService = googleAuthService;
  }

  @RequestMapping("/")
  @Operation(summary = "Homepage", description = "Devuelve un mensaje de bienvenida.")
  public String home() {
    return "Welcome";
  }

  @RequestMapping("/user")
  @Operation(summary = "Obtener usuario autenticado", description = "Devuelve el principal del usuario autenticado.")
  public Principal user(Principal user) {
    return user;
  }

  @GetMapping("/oauth2/callback/google")
  @Operation(summary = "Callback de Google OAuth2", description = "Registra un usuario usando Google OAuth2.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente."),
      @ApiResponse(responseCode = "400", description = "Solicitud no válida."),
      @ApiResponse(responseCode = "409", description = "Conflicto: el Google ID ya está registrado.")
  })
  public ResponseEntity<?> googleCallback(@AuthenticationPrincipal OAuth2User principal) {
    String name = principal.getAttribute("name");
    String email = principal.getAttribute("email");
    String imageProfile = principal.getAttribute("picture");
    String googleId = principal.getAttribute("sub"); // ID único de GoogleAuth
    String imageFacade = principal.getAttribute("picture");
    String biography = "Hola";
    String userName = principal.getAttribute("name") + "2e";

    // Verifica si el usuario ya existe en la base de datos usando el servicio
    Optional<User> existingUser = userService.findUserByEmail(email);
    User user;

    if (existingUser.isPresent()) {
      user = existingUser.get();
    } else {
      // Si no existe, crea un nuevo usuario
      user = new User();
      user.setName(name);
      user.setEmail(email);
      user.setImageProfile(imageProfile);
      user.setTypeAuth(AuthType.Google); // Indica que se autenticó con GoogleAuth
      user.setBiography(biography);
      user.setImageFacade(imageFacade);
      user.setUserName(userName);
      userService.save(user); // Guarda el nuevo usuario usando el servicio
    }

    // Verifica si ya existe la relación GoogleAuth para el googleId
    Optional<GoogleAuth> existingGoogleAuth = googleAuthService.findByGoogleId(googleId);

    if (existingGoogleAuth.isPresent()) {
      // Ya existe un GoogleAuth para este googleId, no es necesario volver a guardar
      return ResponseEntity.status(409).body("El Google ID ya está registrado.");
    }

    // Ahora guarda la relación en GoogleAuth
    GoogleAuth googleAuth = new GoogleAuth();
    googleAuth.setUser(user);
    googleAuth.setGoogleId(googleId);

    googleAuthService.save(googleAuth); // Guarda la relación GoogleAuth

    return ResponseEntity.ok("Usuario registrado exitosamente con Google");
  }
}
