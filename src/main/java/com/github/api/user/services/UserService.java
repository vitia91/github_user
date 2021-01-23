package com.github.api.user.services;

import com.github.api.user.client.RestClient;
import com.github.api.user.client.config.GitHubConfig;
import com.github.api.user.client.config.RestConfig;
import com.github.api.user.mappers.UserMapper;
import com.github.api.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private RestClient restClient;
    @Autowired
    private UserMapper userMapper;

    /**
     * Prepares {@code User} based on login
     *
     * @param login
     * @return return {@code User}
     */
    public User getUser(String login) {
        RestConfig gitConfig = new GitHubConfig(login);
        return userMapper.apply(restClient.call(gitConfig).getBody());
    }
}
