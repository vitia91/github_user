package com.github.api.user.counters.services;

import com.github.api.user.counters.model.UserRequestCounter;
import com.github.api.user.counters.repositories.UserRequestCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserRequestCounterService implements RequestCounter<String> {

    @Autowired
    private UserRequestCounterRepository userRequestCounterRepository;

    @Override
    @Transactional
    public void increaseRequestCounter(String id) {
        Optional<UserRequestCounter> requestCounter = userRequestCounterRepository.findById(id);
        UserRequestCounter userRequestCounter = requestCounter.orElseGet(() -> UserRequestCounter.builder().id(id).build());

        int count = userRequestCounter.getRequestCount() + 1;
        userRequestCounter.setRequestCount(count);

        userRequestCounterRepository.save(userRequestCounter);
    }
}
