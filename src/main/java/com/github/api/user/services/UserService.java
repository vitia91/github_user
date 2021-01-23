package com.github.api.user.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUser(String login) {
        return login;
    }
}
