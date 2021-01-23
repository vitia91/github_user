package com.github.api.user.services;

import com.github.api.user.model.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUser() {
        //given
        String login = "login";
        //when
        User user = userService.getUser(login);
        //then
        assertNotNull("User was not found", user);
        assertEquals("Invalid user", login, user.getLogin());
    }
}