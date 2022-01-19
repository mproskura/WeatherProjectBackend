package com.sda.weatherprojectbackend.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherSource {
    private Long id;
    private String sourceName;
    private String logoFileName;
}
