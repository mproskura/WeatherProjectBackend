package com.sda.weatherprojectbackend.models;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Forecast {
    private Long id;
    private ForecastLocation location;
    private LocalDate forecastDate;
    private LocalDate queryDate;
    private Set<ForecastDetails> forecastDetails;
}
