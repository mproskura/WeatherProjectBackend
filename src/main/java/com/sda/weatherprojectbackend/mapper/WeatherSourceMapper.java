package com.sda.weatherprojectbackend.mapper;

import com.sda.weatherprojectbackend.entities.WeatherSourceEntity;
import com.sda.weatherprojectbackend.models.WeatherSource;

public class WeatherSourceMapper {
    public static WeatherSource sourceFromEntity(WeatherSourceEntity weatherServiceEntity) {
        return WeatherSource.builder()
                .id(weatherServiceEntity.getId())
                .sourceName(weatherServiceEntity.getSourceName())
                .logoFileName(weatherServiceEntity.getLogoFileName())
                .build();
    }

    public static WeatherSourceEntity entityFromWeatherSource(WeatherSource weatherSource) {
        return WeatherSourceEntity.builder()
                .id(weatherSource.getId())
                .sourceName(weatherSource.getSourceName())
                .logoFileName(weatherSource.getLogoFileName())
                .build();
    }
}
