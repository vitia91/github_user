package com.github.api.user.counters;

import com.github.api.user.counters.repositories.UserRequestCounterService;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserAspectTest {

    private static final String LOGIN = "LOGIN";
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

}