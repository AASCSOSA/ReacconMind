package com.ReacconMind.ReacconMind.controller;

import com.ReacconMind.ReacconMind.model.AuthType;
import com.ReacconMind.ReacconMind.model.GoogleAuth;
import com.ReacconMind.ReacconMind.model.User;
import com.ReacconMind.ReacconMind.service.GoogleAuthService;
import com.ReacconMind.ReacconMind.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.security.Principal;
import java.util.Optional;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
public class GoogleAuthController {

    private final UserService userService;
    private final GoogleAuthService googleAuthService;

    public GoogleAuthController(
        UserService userService,
        GoogleAuthService googleAuthService
    ) {
        this.userService = userService;
        this.googleAuthService = googleAuthService;
    }

    @RequestMapping("/")
    @Operation(summary = "Homepage", description = "Returns a welcome message.")
    public String home() {
        return "Welcome";
    }

    @RequestMapping("/user")
    @Operation(
        summary = "Get authenticated user",
        description = "Returns the principal of the authenticated user."
    )
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping("/oauth2/callback/google")
    @Operation(
        summary = "Google OAuth2 Callback",
        description = "Registers a user using Google OAuth2."
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "User successfully registered."
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid request."
            ),
            @ApiResponse(
                responseCode = "409",
                description = "Conflict: Google ID already registered."
            ),
        }
    )
    public ModelAndView googleCallback(
        @AuthenticationPrincipal OAuth2User principal
    ) {
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("email");
        String imageProfile = principal.getAttribute("picture");
        String googleId = principal.getAttribute("sub");
        String imageFacade = principal.getAttribute("picture");
        String biography = "Hi";
        String userName = principal.getAttribute("name") + "2e";

        Optional<User> existingUser = userService.findUserByEmail(email);
        User user;

        if (existingUser.isPresent()) {
            user = existingUser.get();
            return new ModelAndView(
                "redirect:http://localhost:8080/doc/swagger-ui/index.html"
            );
        } else {
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setImageProfile(imageProfile);
            user.setTypeAuth(AuthType.Google);
            user.setBiography(biography);
            user.setImageFacade(imageFacade);
            user.setUserName(userName);
            userService.save(user);
        }

        Optional<GoogleAuth> existingGoogleAuth =
            googleAuthService.findByGoogleId(googleId);

        if (existingGoogleAuth.isPresent()) {
            return new ModelAndView(
                "redirect:http://localhost:8080/doc/swagger-ui/index.html"
            );
        }

        GoogleAuth googleAuth = new GoogleAuth();
        googleAuth.setUser(user);
        googleAuth.setGoogleId(googleId);

        googleAuthService.save(googleAuth);

        return new ModelAndView(
            "redirect:http://localhost:8080/doc/swagger-ui/index.html"
        );
    }
}
