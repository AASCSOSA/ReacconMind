package com.ReacconMind.ReacconMind.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ReacconMind.ReacconMind.model.StatusType;
import com.ReacconMind.ReacconMind.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    public void addUserTest() throws Exception {
        User user = new User();
        user.setName("test");
        user.setImageFacade("testImage.example");
        user.setImageProfile("testProfile.exame");
        user.setBiography("Hi this is example");
        user.setUserName("ExampleUsers1");
        user.setEmail("TestExample1@exampleUser.com");
        user.setPassword("password");

        mvc
            .perform(
                post("/ReacconMind/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(user))
            )
            .andExpect(status().isOk())
            .andExpect(content().string("User added successfully"));
    }

    @Test
    public void updateUserTest() throws Exception {
        User user = new User();
        user.setName("updateTest");
        user.setImageFacade("testImage.example");
        user.setImageProfile("testProfile.exame");
        user.setBiography("Hi this is example update");
        user.setUserName("Update");
        user.setEmail("alan@gmail.com");
        user.setPassword("password");

        mvc
            .perform(
                put("/ReacconMind/users/update/35")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(user))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Updated record"));
    }

    @Test
    public void updateUserStatusTest() throws Exception {
        StatusType status = StatusType.Inactive;

        mvc
            .perform(
                put("/ReacconMind/users/updateStatus/34")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(status))
            )
            .andExpect(status().isOk())
            .andExpect(content().string("User status updated successfully"));
    }
}
