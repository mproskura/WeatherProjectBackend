package com.sda.weatherprojectbackend.configurations;

import com.sda.weatherprojectbackend.entities.WeatherSourceEntity;
import com.sda.weatherprojectbackend.repositories.WeatherSourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitialDatabaseEntriesConfigurer implements ApplicationListener<ContextRefreshedEvent> {

    private final WeatherSourceRepository weatherSourceRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        weatherSourceRepository.save(WeatherSourceEntity.builder()
                .id(1L)
                .sourceName("OpenWeather")
                .logoFileName("OpenWeather.png")
                .build());
        weatherSourceRepository.save(WeatherSourceEntity.builder()
                .id(2L)
                .sourceName("Yr.no")
                .logoFileName("YrNo.png")
                .build());
        weatherSourceRepository.save(WeatherSourceEntity.builder()
                .id(3L)
                .sourceName("Tomorrow.io")
                .logoFileName("TomorrowIo.png")
                .build());
    }
}
