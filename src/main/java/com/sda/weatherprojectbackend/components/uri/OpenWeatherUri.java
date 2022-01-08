package com.sda.weatherprojectbackend.components.uri;

import com.sda.weatherprojectbackend.models.ForecastLocation;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class OpenWeatherUri implements IUri {

    @Override
    public URI get(ForecastLocation location) {
        HttpGet httpGet = new HttpGet("https://api.openweathermap.org/data/2.5/onecall");
        try {
            URI uri = new URIBuilder(httpGet.getURI())
                    .addParameter("lat", String.valueOf(location.getLatitude()))
                    .addParameter("lon", String.valueOf(location.getLongitude()))
                    .addParameter("exclude", "current,minutely,hourly,alerts")
                    .addParameter("appid", "ecb6ba405977c4ee40d4ac55d26b8cb0")
                    .addParameter("units","metric")
                    .build();
            return uri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
