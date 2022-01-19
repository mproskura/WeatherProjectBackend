package com.sda.weatherprojectbackend.controllers;



import com.sda.weatherprojectbackend.models.ForecastLocation;
import com.sda.weatherprojectbackend.services.LocationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@CrossOrigin
@RestController
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/location/{searchString}")
    public List<ForecastLocation> getLocations(@PathVariable String searchString) {
        return locationService.getLocations(searchString);
    }
}
