package com.sda.weatherprojectbackend.components.uri;

import com.sda.weatherprojectbackend.models.ForecastLocation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public class TomorrowIoUri implements IUri {
    @Override
    public Optional<URI> get(ForecastLocation location) {
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append("https://api.tomorrow.io/v4/timelines?location=");
        uriBuilder.append(location.getLatitude());
        uriBuilder.append(",");
        uriBuilder.append(location.getLongitude());
        uriBuilder.append("&fields=temperature,windSpeed,windDirection,cloudCover,pressureSurfaceLevel,humidity" +
                "&timesteps=1h" +
                "&units=metric" +
                "&apikey=gWD2or7SKi7nR89TKKdDa8KKV38Z5Eiw");
        try {
            URI uri = new URI(uriBuilder.toString());
            return Optional.of(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
