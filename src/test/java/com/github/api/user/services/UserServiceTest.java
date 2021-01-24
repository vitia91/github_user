package com.github.api.user.services;

import com.github.api.user.client.RestClient;
import com.github.api.user.client.config.RestConfig;
import com.github.api.user.mappers.UserMapper;
import com.github.api.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    private static final String LOGIN = "LOGIN";
    @MockBean
    private RestClient restClient;
    @MockBean
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUser() {
        //given
        ResponseEntity responseEntityMock = Mockito.mock(ResponseEntity.class);
        when(restClient.call(any())).thenReturn(responseEntityMock);
        Map responseBody = Collections.EMPTY_MAP;
        when(responseEntityMock.getBody()).thenReturn(responseBody);
        User expectedUser = User.builder().build();
        when(userMapper.apply(eq(responseBody))).thenReturn(expectedUser);

        //when
        User user = userService.getUser(LOGIN);
        //then
        assertEquals("Invalid user", expectedUser, user);
        verify(restClient).call(argThat((RestConfig config) -> config.getURI().toString().contains(LOGIN)));
        verify(restClient).call(any(RestConfig.class));
        verify(userMapper).apply(any(Map.class));
    }
}
