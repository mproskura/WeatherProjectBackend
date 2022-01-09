package com.sda.weatherprojectbackend.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "forecast_details")
public class ForecastDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="forecast_details_id")
    private WeatherSourceEntity source;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="forecastDetails")
    private ForecastEntity forecast;
    private double airTemperature;
    private double airPressure;
    private double windSpeed;
    private double windDirection;
    private double cloudiness;
    private double humidity;
}
