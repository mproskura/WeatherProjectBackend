package com.sda.weatherprojectbackend.mapper;

import com.sda.weatherprojectbackend.entities.ForecastLocationEntity;
import com.sda.weatherprojectbackend.models.ForecastLocation;

public class ForecastLocationMapper {

    public static ForecastLocation locationFromEntity(ForecastLocationEntity locationEntity) {
        return ForecastLocation.builder()
                .id(locationEntity.getId())
                .latitude(locationEntity.getLatitude())
                .longitude(locationEntity.getLongitude())
                .type(locationEntity.getType())
                .name(locationEntity.getName())
                .number(locationEntity.getNumber())
                .postal_code(locationEntity.getPostalCode())
                .street(locationEntity.getStreet())
                .confidence(locationEntity.getConfidence())
                .region(locationEntity.getRegion())
                .region_code(locationEntity.getRegionCode())
                .county(locationEntity.getCounty())
                .locality(locationEntity.getLocality())
                .administrative_area(locationEntity.getAdministrativeArea())
                .neighbourhood(locationEntity.getNeighbourhood())
                .country(locationEntity.getCountry())
                .country_code(locationEntity.getCountryCode())
                .continent(locationEntity.getContinent())
                .label(locationEntity.getLabel())
                .build();
    }

    public static ForecastLocationEntity entityFromLocation(ForecastLocation location) {
        return ForecastLocationEntity.builder()
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .type(location.getType())
                .name(location.getName())
                .number(location.getNumber())
                .postalCode(location.getPostal_code())
                .street(location.getStreet())
                .confidence(location.getConfidence())
                .region(location.getRegion())
                .regionCode(location.getRegion_code())
                .county(location.getCounty())
                .locality(location.getLocality())
                .administrativeArea(location.getAdministrative_area())
                .neighbourhood(location.getNeighbourhood())
                .country(location.getCountry())
                .countryCode(location.getCountry_code())
                .continent(location.getContinent())
                .label(location.getLabel())
                .build();
    }
}
