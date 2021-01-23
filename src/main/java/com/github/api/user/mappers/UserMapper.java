package com.github.api.user.mappers;

import com.github.api.user.model.User;
import com.github.api.user.services.UserCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.function.Function;

/**
 * Mapper maps the provided data to {@link User}
 */
@Service
public class UserMapper implements Function<Map, User> {

    protected static final String ID = "id";
    protected static final String LOGIN = "login";
    protected static final String NAME = "name";
    protected static final String TYPE = "type";
    protected static final String AVATAR_URL = "avatar_url";
    protected static final String CREATED_AT = "created_at";
    protected static final String FOLLOWERS = "followers";
    protected static final String PUBLIC_REPOS = "public_repos";

    @Autowired
    private UserCalculationService userCalculationService;

    @Override
    public User apply(Map map) {
        try {
            Integer followers = (Integer) map.get(FOLLOWERS);
            Integer publicRepos = (Integer) map.get(PUBLIC_REPOS);

            return User.builder()
                    .id((Long) map.get(ID))
                    .login(String.valueOf(map.get(LOGIN)))
                    .name(String.valueOf(map.get(NAME)))
                    .type(String.valueOf(map.get(TYPE)))
                    .avatarUrl(new URL(String.valueOf(map.get(AVATAR_URL))))
                    .createdAt(ZonedDateTime.parse(String.valueOf(map.get(CREATED_AT)), DateTimeFormatter.ISO_DATE_TIME))
                    .calculations(userCalculationService.calcUserCalculations(followers, publicRepos))
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
