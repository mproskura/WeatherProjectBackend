package com.sda.weatherprojectbackend.services;


import com.sda.weatherprojectbackend.entities.WeatherSourceEntity;
import com.sda.weatherprojectbackend.repositories.WeatherSourceRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URL;


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

    }

}
