package com.github.api.user.counters.services;

public interface RequestCounter<T> {
    void increaseRequestCounter(T id);
}
