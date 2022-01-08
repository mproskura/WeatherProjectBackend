package com.sda.weatherprojectbackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Forecast {
    private Long id;
    private ForecastLocation location;
    private LocalDate forecastDate;
    private LocalDate queryDate;
    private List<ForecastDetails> forecastDetails;
}