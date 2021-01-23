package com.github.api.user.counters;

import com.github.api.user.counters.repositories.UserRequestCounterService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {

    @Autowired
    private UserRequestCounterService requestCounterService;

    @Before("execution(* com.github.api.user.controllers.UserController.getUser(..))")
    public void increaseRequestCounter(JoinPoint joinPoint) {
        if (joinPoint.getArgs().length >= 1) {
            String login = (String) joinPoint.getArgs()[0];
            requestCounterService.increaseRequestCounter(login);
        }
    }
}