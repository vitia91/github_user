package com.github.api.user.counters.aspects;

import com.github.api.user.counters.services.UserRequestCounterService;
import com.github.api.user.model.User;
import com.github.api.user.services.UserService;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAspectTest {

    private static final String LOGIN = "LOGIN";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRequestCounterService userRequestCounterService;
    @InjectMocks
    private UserAspect userAspect;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWhenLoginDoesNotExist() {
        //given
        JoinPoint joinPoint = Mockito.mock(JoinPoint.class);
        when(joinPoint.getArgs()).thenReturn(new Object[]{});
        //when
        userAspect.increaseRequestCounter(joinPoint);
        //then
        verify(userRequestCounterService, never()).increaseRequestCounter(anyString());
    }

    @Test
    public void testWhenLoginExists() {
        //given
        JoinPoint joinPoint = Mockito.mock(JoinPoint.class);
        when(joinPoint.getArgs()).thenReturn(new Object[]{LOGIN});
        //when
        userAspect.increaseRequestCounter(joinPoint);
        //then
        verify(userRequestCounterService).increaseRequestCounter(eq(LOGIN));
    }

    @Test
    public void testUserAspectInvocation() throws Exception {
        //given
        when(userService.getUser(anyString())).thenReturn(User.builder().build());
        //when
        this.mockMvc.perform(get("/user/" + LOGIN)).andExpect(status().isOk()).andReturn();
        //then
        verify(userRequestCounterService).increaseRequestCounter(eq(LOGIN));
    }
}