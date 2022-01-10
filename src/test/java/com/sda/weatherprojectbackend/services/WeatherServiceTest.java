package com.sda.weatherprojectbackend.services;

import com.sda.weatherprojectbackend.entities.ForecastEntity;
import com.sda.weatherprojectbackend.repositories.ForecastRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class WeatherServiceTest {

    @Autowired
    WeatherService weatherService;

    @Autowired
    ForecastRepository forecastRepository;

    @Test
    void getForecast() {
        LocalDate testDate = LocalDate.of(2022,1,9);
        System.out.println(weatherService.getForecast(88L, testDate).toString());

    }

    @Test
    void getForecastWithId() {
        Optional<ForecastEntity> forecast = forecastRepository.findById(7L);
        System.out.println(forecast);
    }
}