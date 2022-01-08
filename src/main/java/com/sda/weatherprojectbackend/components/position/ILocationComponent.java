package com.sda.weatherprojectbackend.components.position;

import com.sda.weatherprojectbackend.models.ForecastLocation;

import java.util.List;


//todo przerobic na component
public interface ILocationComponent {
    public List<ForecastLocation> findLocations(String locationQuery);
}
