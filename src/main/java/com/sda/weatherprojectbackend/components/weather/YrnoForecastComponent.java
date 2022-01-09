package com.sda.weatherprojectbackend.components.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weatherprojectbackend.components.uri.YrnoUri;
import com.sda.weatherprojectbackend.models.ForecastDetails;
import com.sda.weatherprojectbackend.models.ForecastLocation;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Optional;

public class YrnoForecastComponent implements IWeatherForecastComponent {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Optional<ForecastDetails> getForecast(ForecastLocation location, LocalDate forecastDate) {
        URI uri = new YrnoUri().get(location);

        Optional<HttpResponse<String>> responseOptional = HttpRequest.getResponse(uri);
        if (responseOptional.isPresent()) {
            HttpResponse<String> response = responseOptional.get();
            try {
                JsonNode results = mapper.readTree(response.body());
                JsonNode timeseries = results.get("properties").get("timeseries");
                Iterator<JsonNode> elements = timeseries.elements();

                JsonNode forecast;
                LocalTime twelveOClock = LocalTime.of(12, 00, 00);
                LocalDateTime forecastDateTime = LocalDateTime.of(forecastDate, twelveOClock);

                for (Iterator<JsonNode> it = elements; it.hasNext(); ) {
                    JsonNode element = it.next();
                    String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                    LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(element.get("time").toString().replace("\"", "")));

                    if (localDateTime.equals(forecastDateTime)) {
                        forecast = element.get("data").get("instant").get("details");

                        ForecastDetails weatherForecast = ForecastDetails.builder()
                                .airPressure(Double.valueOf(forecast.get("air_pressure_at_sea_level").toString()))
                                .airTemperature(Double.valueOf(forecast.get("air_temperature").toString()))
                                .cloudiness(Double.valueOf(forecast.get("cloud_area_fraction").toString()))
                                .humidity(Double.valueOf(forecast.get("relative_humidity").toString()))
                                .windDirection(Double.valueOf(forecast.get("wind_from_direction").toString()))
                                .windSpeed(Double.valueOf(forecast.get("wind_speed").toString()))
                                .build();
                        return Optional.of(weatherForecast);
                    }
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
}