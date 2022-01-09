package com.sda.weatherprojectbackend.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class WeatherServiceTest {

    @Autowired
    WeatherService weatherService;

    @Test
    void getForecast() {
        LocalDate testDate = LocalDate.of(2022,1,9);
        System.out.println(weatherService.getForecast(88L, testDate).toString());

    }
}