package com.sda.weatherprojectbackend.controllers;

import com.sda.weatherprojectbackend.models.Forecast;
import com.sda.weatherprojectbackend.services.WeatherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@CrossOrigin
@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{locationId}/{forecastDate}")
    public Forecast getForecast(@PathVariable long locationId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate forecastDate) {
        return weatherService.getForecast(locationId, forecastDate);
    }
}
