package com.github.api.user.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

class UserCalculationServiceTest {

    @InjectMocks
    private UserCalculationService userCalculationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculation() {
        //given
        int followers = 6;
        int publicRepo = 2;
        Double expectedResult = 4.;
        //when
        Double result = userCalculationService.calcUserCalculations(followers, publicRepo);
        //then
        assertEquals("Invalid calculation", expectedResult, result);
    }

    @Test
    public void testCalculationWhenFollowersAre0() {
        //given
        int followers = 0;
        int publicRepo = 2;
        //when
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userCalculationService.calcUserCalculations(followers, publicRepo)
        );
    }
}