package com.sda.weatherprojectbackend.components.uri;

import com.sda.weatherprojectbackend.models.ForecastLocation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public class YrnoUri implements IUri {

    @Override
    public Optional<URI> get(ForecastLocation location) {
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=");
        uriBuilder.append(location.getLatitude());
        uriBuilder.append("&lon=");
        uriBuilder.append(location.getLongitude());
        try {
            URI uri = new URI(uriBuilder.toString());
            return Optional.of(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
