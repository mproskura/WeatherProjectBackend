package com.sda.weatherprojectbackend.components.uri;

import com.sda.weatherprojectbackend.models.ForecastLocation;

import java.net.URI;

public interface IUri {
    URI get(ForecastLocation location);
}
