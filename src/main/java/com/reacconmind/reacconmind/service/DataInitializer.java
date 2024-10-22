package com.reacconmind.reacconmind.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.reacconmind.reacconmind.model.AuthType;
import com.reacconmind.reacconmind.model.StatusType;
import com.reacconmind.reacconmind.model.ThemeBotType;
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
                "I am a professor.",
                "professor",
                AuthType.Email,
                StatusType.Active,
                ThemeType.Light,
                ThemeBotType.CombinatedMedia);

        createUser(
                "john.doe@example.com",
                "John Doe",
                "john123",
                "Hello, I'm John.",
                "john_doe",
                AuthType.Email,
                StatusType.Active,
                ThemeType.Light,
                ThemeBotType.CombinatedMedia);

        createUser(
                "jane.doe@example.com",
                "Jane Doe",
                "jane123",
                "Hi, I'm Jane.",
                "jane_doe",
                AuthType.Email,
                StatusType.Active,
                ThemeType.Light,
                ThemeBotType.CombinatedMedia);
    }

    private void createUser(
            String email,
            String name,
            String plainPassword,
            String biography,
            String userName,
            AuthType typeAuth,
            StatusType status,
            ThemeType theme,
            ThemeBotType themeBot) {
        Optional<User> existingUser = userService.findUserByEmail(email);

        if (!existingUser.isPresent()) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(plainPassword);
            user.setUserName(userName);
            user.setBiography(biography);
            user.setImageProfile(user.getImageProfile()); // Usa el valor predeterminado
            user.setImageFacade(user.getImageFacade()); // Usa el valor predeterminado
            user.setThumbnail(user.getThumbnail()); // Usa el valor predeterminado
            user.setTypeAuth(typeAuth != null ? typeAuth : AuthType.Email); // Usa el valor predeterminado si es nulo
            user.setStatus(status != null ? status : StatusType.Active); // Usa el valor predeterminado si es nulo
            user.setTheme(theme != null ? theme : ThemeType.Light); // Usa el valor predeterminado si es nulo
            user.setThemeBot(themeBot != null ? themeBot : ThemeBotType.CombinatedMedia); // Usa el valor predeterminado
            userService.save(user);
        }
    }
}
