package com.ReacconMind.ReacconMind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ReacconMind.ReacconMind.model.User;
import com.ReacconMind.ReacconMind.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/ReacconMind/users")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
                RequestMethod.PUT })
public class UserController {

        @Autowired
        private UserService userService;

        @Operation(summary = "Get all Users")
        @ApiResponse(responseCode = "200", description = "Found Users", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))) })
        @GetMapping
        public List<User> getAll() {
                return userService.getAll();
        }

        @Operation(summary = "Get a user by his or her control id User")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "User found", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
                        @ApiResponse(responseCode = "400", description = "Invalid id User supplied", content = @Content),
                        @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
        @GetMapping("/{idUser}")
        public ResponseEntity<?> getByIdUser(@PathVariable Integer idUser) {
                User user = userService.getByIdUser(idUser);
                return new ResponseEntity<User>(user, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<String> addUser(@RequestBody User user) {
                userService.save(user); 
                return ResponseEntity.ok("User added successfully");
        }

}
