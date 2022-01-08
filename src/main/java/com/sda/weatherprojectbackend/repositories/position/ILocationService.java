package com.sda.weatherprojectbackend.repositories.position;

import com.sda.weatherprojectbackend.models.ForecastLocation;

import java.util.List;


//todo przerobic na component
public interface ILocationService {
    public List<ForecastLocation> findLocations(String locationQuery);
}
