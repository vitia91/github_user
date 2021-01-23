package com.github.api.user.controllers;

import com.github.api.user.model.User;
import com.github.api.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{login}")
    @ResponseBody
    public User getUser(@PathVariable("login") String login) {
        return userService.getUser(login);
    }
}
