package com.sda.weatherprojectbackend.components.weather;

import com.sda.weatherprojectbackend.models.ForecastDetails;
import com.sda.weatherprojectbackend.models.ForecastLocation;

import java.time.LocalDate;
import java.util.Optional;

public interface IWeatherForecastComponent {
    Optional<ForecastDetails> getForecast(ForecastLocation location, LocalDate forecastDate);
}
