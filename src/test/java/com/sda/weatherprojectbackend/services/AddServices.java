package com.sda.weatherprojectbackend.services;


import com.sda.weatherprojectbackend.entities.WeatherSourceEntity;
import com.sda.weatherprojectbackend.models.WeatherSource;
import com.sda.weatherprojectbackend.repositories.WeatherSourceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddServices {

    @Autowired
    WeatherSourceRepository weatherSourceRepository;

    @Test
    void add() {
        weatherSourceRepository.save(WeatherSourceEntity.builder()
                .sourceName("OpenWeather")
                .build());
        weatherSourceRepository.save(WeatherSourceEntity.builder()
                .sourceName("Yr.no")
                .build());

    }

}
