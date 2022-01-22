package com.sda.weatherprojectbackend.components.weather;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ForecastDateTime {

    public static LocalDateTime getForecastDateTime(LocalDate forecastDate) {
        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalTime forecastTime = LocalTime.of(12, 00, 00);
        if (forecastDate.equals(today) && currentTime.isAfter(forecastTime)) {
            forecastTime = LocalTime.of(currentTime.plusHours(1).getHour(), 0, 0);
        }
        return LocalDateTime.of(forecastDate, forecastTime);
    }

}
