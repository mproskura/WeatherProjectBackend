package com.sda.weatherprojectbackend.services;

import com.sda.weatherprojectbackend.models.Forecast;

import java.time.LocalDate;

public interface IWeatherService {
    Forecast getForecast(long locationId, LocalDate forecastDate);
}
