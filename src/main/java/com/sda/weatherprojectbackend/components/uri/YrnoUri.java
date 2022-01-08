package com.sda.weatherprojectbackend.components.uri;

import com.sda.weatherprojectbackend.models.ForecastLocation;

import java.net.URI;
import java.net.URISyntaxException;

public class YrnoUri implements IUri {

    @Override
    public URI get(ForecastLocation location) {
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=");
        uriBuilder.append(location.getLatitude());
        uriBuilder.append("&lon=");
        uriBuilder.append(location.getLongitude());
        try {
            URI uri = new URI(uriBuilder.toString());
            return uri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
