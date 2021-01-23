package com.github.api.user.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Test
    public void testUser() {
        //given
        String login = "login";
        //when
        String user = userService.getUser(login);
        //then
        assertEquals("Invalid user", login, user);
    }
}