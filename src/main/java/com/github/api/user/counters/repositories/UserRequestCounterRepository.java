package com.github.api.user.counters.repositories;

import com.github.api.user.counters.model.UserRequestCounter;
import org.springframework.data.repository.CrudRepository;

public interface UserRequestCounterRepository extends CrudRepository<UserRequestCounter, String> {
}

