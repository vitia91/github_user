package com.github.api.user.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.api.user.model.User;
import com.github.api.user.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private static final String LOGIN = "login";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    @Test
    public void testGetUser() throws Exception {
        //given
        User user = User.builder().login(LOGIN).build();
        when(userService.getUser(eq(LOGIN))).thenReturn(user);
        //when
        MvcResult result = this.mockMvc.perform(get("/user/" + LOGIN))
                .andExpect(status().isOk()).andReturn();
        User resultUser = objectMapper.readValue(result.getResponse().getContentAsByteArray(), User.class);
        //then
        verify(userService).getUser(eq(LOGIN));
        assertEquals("Invalid user", user, resultUser);
    }
}