package com.reacconmind.reacconmind.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser; // Importa la anotación
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
    @WithMockUser(username = "testuser", roles = { "USER" })
    void testResetPasswordRequest() throws Exception {
        String email = "mario.pv@teziutlan.tecnm.mx";

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
    @WithMockUser(username = "testuser", roles = { "USER" })
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
