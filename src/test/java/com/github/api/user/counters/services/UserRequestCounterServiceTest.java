package com.github.api.user.counters.services;

import com.github.api.user.counters.model.UserRequestCounter;
import com.github.api.user.counters.repositories.UserRequestCounterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRequestCounterServiceTest {

    private static final String LOGIN_1 = "LOGIN_1";
    private static final String LOGIN_2 = "LOGIN_2";
    @MockBean
    private UserRequestCounterRepository userRequestCounterRepository;
    @InjectMocks
    private UserRequestCounterService userRequestCounterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWhenRowWasFound() {
        //given
        UserRequestCounter userRequestCounter = UserRequestCounter.builder().id(LOGIN_1).build();
        int expectedCount = userRequestCounter.getRequestCount() + 1;
        when(userRequestCounterRepository.findById(any())).thenReturn(Optional.of(userRequestCounter));
        //when
        userRequestCounterService.increaseRequestCounter(LOGIN_2);
        //then
        verify(userRequestCounterRepository).save(argThat((UserRequestCounter urc) ->
                urc.getId().equals(userRequestCounter.getId()) && urc.getRequestCount() == expectedCount));

    }

    @Test
    public void testWhenRowWasNotFound() {
        //given
        int expectedCount = 1;
        when(userRequestCounterRepository.findById(any())).thenReturn(Optional.empty());
        //when
        userRequestCounterService.increaseRequestCounter(LOGIN_2);
        //then
        verify(userRequestCounterRepository).save(argThat((UserRequestCounter urc) ->
                urc.getId().equals(LOGIN_2) && urc.getRequestCount() == expectedCount));
    }
}