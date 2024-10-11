package com.ReacconMind.ReacconMind.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    public void getAllTest() throws Exception {
        mvc
            .perform(
                get("/ReacconMind/users").accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0)))
            );
    }

    @Test
    public void getAllActiveUsersTest() throws Exception {
        mvc
            .perform(
                get("/ReacconMind/users/usersActive").accept(
                    MediaType.APPLICATION_JSON
                )
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0)))
            );
    }

    @Test
    public void getByIdUserTest() throws Exception {
        mvc
            .perform(
                get("/ReacconMind/users/32").accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.idUser", is(32)));
    }

    @Test
    public void getByIdUserNotFoundTest() throws Exception {
        mvc
            .perform(
                get("/ReacconMind/users/0").accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isNotFound());
    }
}
