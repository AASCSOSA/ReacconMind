package com.ReacconMind.ReacconMind.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordResetTokenTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PasswordResetTokenController paswordTokenController;

    @Test
    void contextLoads() throws Exception {
        assertThat(paswordTokenController).isNotNull();
    }

    @Test
    void testResetPasswordRequest() throws Exception {
        String email = "alanhuracan2010@gmail.com";

        mvc
            .perform(
                post("/password/reset-request")
                    .param("email", email)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            )
            .andExpect(status().isOk())
            .andExpect(content().string("Request processed"));
    }

    @Test
    void testResetPasswordIncorrectRequest() throws Exception {
        String invalidEmail = "invalid-email@example.com";

        mvc
            .perform(
                post("/password/reset-request")
                    .param("email", invalidEmail)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            )
            .andExpect(status().isNotFound())
            .andExpect(content().string("User not found for email"));
    }
}
