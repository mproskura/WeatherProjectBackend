package com.sda.weatherprojectbackend.components.weather;

public enum WeatherService {
    YRNO(1),
    OPENWEATHER(2);

    private long id;

    private WeatherService(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
