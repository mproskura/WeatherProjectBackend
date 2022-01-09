package com.sda.weatherprojectbackend.mapper;

import com.sda.weatherprojectbackend.entities.ForecastDetailsEntity;
import com.sda.weatherprojectbackend.models.ForecastDetails;

public class ForecastDetailsMapper {

    public static ForecastDetailsEntity entityFromForecastDetails(ForecastDetails forecastDetails) {
        return ForecastDetailsEntity.builder()
                .id(forecastDetails.getId())
                .source(WeatherSourceMapper.entityFromWeatherSource(forecastDetails.getWeatherSource()))
                .airTemperature(forecastDetails.getAirTemperature())
                .airPressure(forecastDetails.getAirPressure())
                .windSpeed(forecastDetails.getWindSpeed())
                .windDirection(forecastDetails.getWindDirection())
                .cloudiness(forecastDetails.getCloudiness())
                .humidity(forecastDetails.getHumidity())
                .build();
    }

    public static ForecastDetails forecastDetailsFromEntity(ForecastDetailsEntity forecastDetailsEntity) {
        return ForecastDetails.builder()
                .id(forecastDetailsEntity.getId())
                .weatherSource(WeatherSourceMapper.sourceFromEntity(forecastDetailsEntity.getSource()))
                .airTemperature(forecastDetailsEntity.getAirTemperature())
                .airPressure(forecastDetailsEntity.getAirPressure())
                .windSpeed(forecastDetailsEntity.getWindSpeed())
                .windDirection(forecastDetailsEntity.getWindDirection())
                .cloudiness(forecastDetailsEntity.getCloudiness())
                .humidity(forecastDetailsEntity.getHumidity())
                .build();
    }
}
