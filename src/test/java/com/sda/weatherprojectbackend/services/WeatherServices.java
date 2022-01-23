package com.sda.weatherprojectbackend.services;


import com.sda.weatherprojectbackend.entities.WeatherSourceEntity;
import com.sda.weatherprojectbackend.repositories.WeatherSourceRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class WeatherServices {

    @Autowired
    WeatherSourceRepository weatherSourceRepository;

    @Test
    void add() {
        weatherSourceRepository.save(WeatherSourceEntity.builder()
                .sourceName("OpenWeather")
                .logoFileName("OpenWeather.png")
                .build());
        weatherSourceRepository.save(WeatherSourceEntity.builder()
                .sourceName("Yr.no")
                .logoFileName("YrNo.png")
                .build());
        weatherSourceRepository.save(WeatherSourceEntity.builder()
                .sourceName("Tomorrow.io")
                .logoFileName("TomorrowIo.png")
                .build());

    }

}
