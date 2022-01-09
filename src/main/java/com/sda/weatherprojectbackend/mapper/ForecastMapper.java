package com.sda.weatherprojectbackend.mapper;

import com.sda.weatherprojectbackend.entities.ForecastEntity;
import com.sda.weatherprojectbackend.models.Forecast;
import java.util.stream.Collectors;

public class ForecastMapper {

    public static ForecastEntity entityFromForecast(Forecast forecast) {
        return ForecastEntity.builder()
                .id(forecast.getId())
                .location(ForecastLocationMapper.entityFromLocation(forecast.getLocation()))
                .forecastDate(forecast.getForecastDate())
                .queryDate(forecast.getQueryDate())
                .forecastDetails(forecast.getForecastDetails().stream().map(forecastDetails ->
                                ForecastDetailsMapper.entityFromForecastDetails(forecastDetails))
                        .collect(Collectors.toList()))
                .build();
    }

    public static Forecast forecastFromEntity(ForecastEntity forecastEntity) {
        return Forecast.builder()
                .id(forecastEntity.getId())
                .location(ForecastLocationMapper.locationFromEntity(forecastEntity.getLocation()))
                .forecastDate(forecastEntity.getForecastDate())
                .queryDate(forecastEntity.getQueryDate())
                .forecastDetails(forecastEntity.getForecastDetails().stream().map(forecastDetailsEntity ->
                                ForecastDetailsMapper.forecastDetailsFromEntity(forecastDetailsEntity))
                        .collect(Collectors.toList()))
                .build();
    }
}
