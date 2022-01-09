package com.sda.weatherprojectbackend.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "weather_source")
public class WeatherSourceEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="service_name")
    private String sourceName;
    @OneToMany(mappedBy = "source")
    @Column(name="service")
    private List<ForecastDetailsEntity> forecastDetails;
}
