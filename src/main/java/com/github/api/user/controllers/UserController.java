package com.github.api.user.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/{login}")
    public @ResponseBody
    String getUser(@PathVariable("login") String login) {
        return login;
    }
}
