package com.reacconmind.reacconmind.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.reacconmind.reacconmind.model.AuthType;
import com.reacconmind.reacconmind.model.StatusType;
import com.reacconmind.reacconmind.model.ThemeType;
import com.reacconmind.reacconmind.model.User;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;


    @Override
    public void run(String... args) throws Exception {
        createDefaultUsers();
    }

    private void createDefaultUsers() {
        createUser(
            "mario.pv@teziutlan.tecnm.mx",
            "Professor",
            "mario",
            "default_image_url",
            "default_image_url",
            "I am a professor.",
            "professor",
            AuthType.Email,
            StatusType.Active,
            ThemeType.Light
        );

        createUser(
            "john.doe@example.com",
            "John Doe",
            "john123",
            "default_image_url",
            "default_image_url",
            "Hello, I'm John.",
            "john_doe",
            AuthType.Email,
            StatusType.Active,
            ThemeType.Light
        );

        createUser(
            "jane.doe@example.com",
            "Jane Doe",
            "jane123",
            "default_image_url",
            "default_image_url",
            "Hi, I'm Jane.",
            "jane_doe",
            AuthType.Email,
            StatusType.Active,
            ThemeType.Light
        );
    }

    private void createUser(
        String email,
        String name,
        String plainPassword,
        String imageProfile,
        String imageFacade,
        String biography,
        String userName,
        AuthType typeAuth,
        StatusType status,
        ThemeType theme
    ) {
        Optional<User> existingUser = userService.findUserByEmail(email);

        if (!existingUser.isPresent()) {
            User user = new User(
                name,
                email,
                plainPassword,
                imageProfile,
                imageFacade,
                biography,
                userName,
                typeAuth,
                status,
                theme
            );
            userService.save(user);
        }
    }
}
