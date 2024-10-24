package com.reacconmind.reacconmind.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reacconmind.reacconmind.model.StatusType;
import com.reacconmind.reacconmind.model.User;
import com.reacconmind.reacconmind.service.UserService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/ReacconMind/users")
@CrossOrigin(origins = "*", methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.DELETE,
                RequestMethod.PUT,
})
@Configuration
@OpenAPIDefinition(info = @Info(title = "ReacconMind API", description = "API for user management in the ReacconMind application", version = "1.0"))
public class UserController {

        @Autowired
        private UserService userService;
      
        @Operation(summary = "Get all Users", description = "Get a list of all registered users.")
        @ApiResponse(responseCode = "200", description = "List of users obtained successfully", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))),
        })
        @GetMapping
        public List<User> getAll() {
                return userService.getAll();
        }

        @Operation(summary = "Get all Users with pagination", description = "Retrieve a paginated list of users. Specify the page number and page size to get a subset of users.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successful retrieval of users"),
                        @ApiResponse(responseCode = "400", description = "Invalid page number or page size provided"),
                        @ApiResponse(responseCode = "500", description = "Internal server error")
        })
        @GetMapping(value = "pagination", params = { "page", "pageSize" })
        public List<User> getAllPaginated(
                        @Parameter(description = "The page number to retrieve. Default is 0 (first page).", required = false, example = "1") @RequestParam(value = "page", defaultValue = "0", required = false) int page,

                        @Parameter(description = "The number of users per page. Default is 10.", required = false, example = "5") @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
                List<User> users = userService.getAll(page, pageSize);
                return users;
        }

        @Operation(summary = "Get all active Users", description = "Get a list of all registered active users.")
        @ApiResponse(responseCode = "200", description = "List of active users obtained successfully", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))),
        })
        @GetMapping("/usersActive")
        public List<User> getAllUserActive() {
                return userService.getAllActive();
        }

        @Operation(summary = "Get a user by ID", description = "Get a specific user by their control ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "User found", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)),
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid user ID", content = @Content),
                        @ApiResponse(responseCode = "401", description = "Authentication failure", content = @Content(schema = @Schema(hidden = true))),
                        @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
        })
        @GetMapping("/{idUser}")
        public ResponseEntity<?> getByIdUser(@PathVariable Integer idUser) {
                User user = userService.getByIdUser(idUser);
                return ResponseEntity.ok(user);
        }

        @Operation(summary = "Add a new User", description = "Add a new user to the system.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "User added successfully", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)),
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid user data", content = @Content),
        })
        @PreAuthorize("permitAll()")
        @PostMapping
        public ResponseEntity<String> addUser(@RequestBody User user) {
                userService.save(user);
                return ResponseEntity.ok("User added successfully");
        }

        @Operation(summary = "Update an existing User", description = "Update the data of an existing user based on their ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Update successfully", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)),
                        }),
                        @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
                        @ApiResponse(responseCode = "400", description = "Invalid user data", content = @Content),
        })
        @PutMapping("/update/{idUser}")
        public ResponseEntity<?> update(
                        @RequestBody User user,
                        @PathVariable Integer idUser) {
                User auxUser = userService.getByIdUser(idUser);
                user.setIdUser(auxUser.getIdUser());
                userService.save(user);
                return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        }

        @Operation(summary = "Update user status", description = "Update the status of an existing user based on their ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "User status updated successfully", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)),
                        }),
                        @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
                        @ApiResponse(responseCode = "400", description = "Invalid status data", content = @Content),
        })
        @PutMapping("/updateStatus/{idUser}")
        public ResponseEntity<?> updateUserStatus(
                        @RequestBody StatusType status,
                        @PathVariable Integer idUser) {
                User user = userService.getByIdUser(idUser);
                if (user == null) {
                        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
                }
                user.setStatus(status);
                userService.save(user);
                return new ResponseEntity<>(
                                "User status updated successfully",
                                HttpStatus.OK);
        }
}
