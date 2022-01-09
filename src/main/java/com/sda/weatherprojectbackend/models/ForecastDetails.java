package com.sda.weatherprojectbackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDetails {
    private Long id;
    private WeatherSource weatherSource;
    private double airTemperature;
    private double airPressure;
    private double windSpeed;
    private double windDirection;
    private double cloudiness;
    private double humidity;
}
