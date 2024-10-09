package com.ReacconMind.ReacconMind.controller;

import com.ReacconMind.ReacconMind.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ReacconMind.ReacconMind.model.User;

@RestController
@PreAuthorize("hasRole('USER')") // o el rol correspondiente que se necesite
@RequestMapping("/ReacconMind/users")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
    RequestMethod.PUT })
@Configuration
@OpenAPIDefinition(info = @Info(title = "ReacconMind API", description = "API para la gestión de usuarios en la aplicación ReacconMind", version = "1.0"))
public class UserController {

  @Autowired
  private UserService userService;

  @Operation(summary = "Get all Users", description = "Obtiene una lista de todos los usuarios registrados.")
  @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente", content = {
      @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))) })
  @GetMapping
  public List<User> getAll() {
    return userService.getAll();
  }

  @Operation(summary = "Get all active Users", description = "Obtiene una lista de todos los usuarios activos registrados.")
  @ApiResponse(responseCode = "200", description = "Lista de usuarios activos obtenida correctamente", content = {
      @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))) })
  @GetMapping("/usersActive")
  public List<User> getAllUserActive() {
    return userService.getAllActive();
  }

  @Operation(summary = "Get a user by ID", description = "Obtiene un usuario específico por su ID de control.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
      @ApiResponse(responseCode = "400", description = "ID de usuario inválido", content = @Content),
      @ApiResponse(responseCode = "401", description = "Fallo de autenticación", content = @Content(schema = @Schema(hidden = true))),
      @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content) })
  @GetMapping("/{idUser}")
  public ResponseEntity<?> getByIdUser(@PathVariable Integer idUser) {
    User user = userService.getByIdUser(idUser);
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @Operation(summary = "Add a new User", description = "Agrega un nuevo usuario al sistema.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Usuario agregado exitosamente", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
      @ApiResponse(responseCode = "400", description = "Datos de usuario inválidos", content = @Content),
  })
  @PreAuthorize("permitAll()")
  @PostMapping
  public ResponseEntity<String> addUser(@RequestBody User user) {
    userService.save(user);
    return ResponseEntity.ok("User added successfully");
  }

  @Operation(summary = "Update an existing User", description = "Actualiza los datos de un usuario existente basado en su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Update successfully", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
      @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
      @ApiResponse(responseCode = "400", description = "Datos de usuario inválidos", content = @Content) })
  @PutMapping("/update/{idUser}")
  public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer idUser) {
    User auxUser = userService.getByIdUser(idUser);
    user.setIdUser(auxUser.getIdUser());
    userService.save(user);
    return new ResponseEntity<String>("Updated record", HttpStatus.OK);
  }
}
