package com.github.api.user.mappers;

import com.github.api.user.model.User;
import com.github.api.user.services.UserCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserMapperTest {

    private static final String URI = "http://localhost";
    private static Long ID = 1L;
    private static String LOGIN = "LOGIN";
    private static String USER_NAME = "USER_NAME";
    private static String USER_TYPE = "USER_TYPE";
    private static String AVATAR_URL = "http://avatar.com";

    @MockBean
    private UserCalculationService userCalculationService;
    @InjectMocks
    private UserMapper userMapper;

    private Map map;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        map = new HashMap();
        map.put(UserMapper.AVATAR_URL, URI);
        map.put(UserMapper.CREATED_AT, ZonedDateTime.now());
    }

    @Test
    public void testUserId() {
        //given
        map.put(UserMapper.ID, ID);
        //when
        User user = userMapper.apply(map);
        //then
        assertEquals("Invalid ID", ID, user.getId());
    }

    @Test
    public void testUserLogin() {
        //given
        map.put(UserMapper.LOGIN, LOGIN);
        //when
        User user = userMapper.apply(map);
        //then
        assertEquals("Invalid login", LOGIN, user.getLogin());
    }

    @Test
    public void testUserName() {
        //given
        map.put(UserMapper.NAME, USER_NAME);
        //when
        User user = userMapper.apply(map);
        //then
        assertEquals("Invalid name", USER_NAME, user.getName());
    }

    @Test
    public void testUserType() {
        //given
        map.put(UserMapper.TYPE, USER_TYPE);
        //when
        User user = userMapper.apply(map);
        //then
        assertEquals("Invalid user type", USER_TYPE, user.getType());
    }

    @Test
    public void testUserAvatarUrl() {
        //given
        map.put(UserMapper.AVATAR_URL, AVATAR_URL);
        //when
        User user = userMapper.apply(map);
        //then
        assertEquals("Invalid avatarURL", AVATAR_URL, user.getAvatarUrl().toString());
    }

    @Test
    public void testUserCreated() {
        //given
        ZonedDateTime dataTime = ZonedDateTime.now();
        map.put(UserMapper.CREATED_AT, dataTime);
        //when
        User user = userMapper.apply(map);
        //then
        assertEquals("Invalid created date", dataTime, user.getCreatedAt());
    }

    @Test
    public void testUserCalculations() {
        //given
        int followers = 5;
        int publicRepos = 10;
        map.put(UserMapper.FOLLOWERS, followers);
        map.put(UserMapper.PUBLIC_REPOS, publicRepos);
        //when
        User user = userMapper.apply(map);
        //then
        assertNotNull("Calculations was not funded", user.getCalculations());
        verify(userCalculationService).calcUserCalculations(eq(followers), eq(publicRepos));
    }
}
