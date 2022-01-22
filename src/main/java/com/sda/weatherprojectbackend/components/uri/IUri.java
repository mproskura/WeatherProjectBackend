package com.sda.weatherprojectbackend.components.uri;

import com.sda.weatherprojectbackend.models.ForecastLocation;

import java.net.URI;
import java.util.Optional;

public interface IUri {
    Optional<URI> get(ForecastLocation location);
}
