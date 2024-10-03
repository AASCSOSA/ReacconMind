package com.ReacconMind.ReacconMind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ReacconMind.ReacconMind.model.User;
import com.ReacconMind.ReacconMind.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/ReacconMind/User")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
                RequestMethod.PUT })
public class UserController {

        @Autowired
        private UserService userService;

        @GetMapping
        public List<User> getAll() {
                return userService.getAll();
        }

        @GetMapping("/{idUser}")
        public ResponseEntity<?> getByIdUser(@PathVariable Integer idUser) {
                User user = userService.getByIdUser(idUser);
                return new ResponseEntity<User>(user, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<?> addUSer(@RequestBody User user) {
                userService.save(user);
                return new ResponseEntity<String>("Save", HttpStatus.OK);
        }

}
