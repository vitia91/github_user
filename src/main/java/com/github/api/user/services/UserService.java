package com.github.api.user.services;

import com.github.api.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUser(String login) {
        return User.builder().login(login).build();
    }
}
