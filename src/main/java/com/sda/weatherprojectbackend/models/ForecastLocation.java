package com.sda.weatherprojectbackend.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ForecastLocation {
    private Long id;
    private double latitude;
    private double longitude;
    String type;
    String name;
    String number;
    String postal_code;
    String street;
    String confidence;
    String region;
    String region_code;
    String county;
    String locality;
    String administrative_area;
    String neighbourhood;
    String country;
    String country_code;
    String continent;
    String label;
}
