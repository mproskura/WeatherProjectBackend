package com.sda.weatherprojectbackend.services;

import com.sda.weatherprojectbackend.models.Forecast;

import java.time.LocalDate;

public class WeatherService implements IWeatherService{
    @Override
    public Forecast getForecast(long locationId, LocalDate forecastDate) {
        return null;
    }
}
