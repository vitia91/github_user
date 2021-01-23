package com.github.api.user.services;

import org.springframework.stereotype.Service;

@Service
public class UserCalculationService {

    public Double calcUserCalculations(Integer followers, Integer publicRepos) {
        if (followers == 0) {
            throw new IllegalArgumentException("followers cannot be 0");
        }
        return 6. / followers * (2 + publicRepos);
    }
}
