package com.sda.weatherprojectbackend.services;



import com.sda.weatherprojectbackend.models.ForecastLocation;

import java.util.List;

public interface ILocationService {
    List<ForecastLocation> getLocations(String locationQuery);
}
