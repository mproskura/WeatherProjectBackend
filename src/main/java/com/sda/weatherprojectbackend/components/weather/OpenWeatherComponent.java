package com.sda.weatherprojectbackend.components.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weatherprojectbackend.components.uri.OpenWeatherUri;
import com.sda.weatherprojectbackend.models.ForecastDetails;
import com.sda.weatherprojectbackend.models.ForecastLocation;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.Optional;

public class OpenWeatherComponent implements IWeatherForecastComponent {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Optional<ForecastDetails> getForecast(ForecastLocation location, LocalDate forecastDate) {

        URI uri = new OpenWeatherUri().get(location);

        Optional<HttpResponse<String>> responseOptional = HttpRequest.getResponse(uri);
        if (responseOptional.isPresent()) {
            HttpResponse<String> response = responseOptional.get();

            LocalTime localTime = LocalTime.of(8, 0, 0);
            long forecastEpoch = LocalDateTime.of(forecastDate, localTime).toEpochSecond(ZoneOffset.UTC);

            JsonNode results = null;
            try {
                results = mapper.readTree(response.body()).get("daily");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            Iterator<JsonNode> elements = results.elements();
            for (Iterator<JsonNode> it = elements; it.hasNext(); ) {
                JsonNode element = it.next();

                if (Long.valueOf(String.valueOf(element.get("dt"))) >= forecastEpoch) {

                    return Optional.of(ForecastDetails.builder()
                            .windSpeed(Double.valueOf(element.get("wind_speed").toString()))
                            .windDirection(Double.valueOf(element.get("wind_deg").toString()))
                            .airTemperature(Double.valueOf(element.get("temp").get("day").toString()))
                            .airPressure(Double.valueOf(element.get("pressure").toString()))
                            .cloudiness(Double.valueOf(element.get("clouds").toString()))
                            .humidity(Double.valueOf(element.get("humidity").toString()))
                            .build());
                }
            }
        }
        return Optional.empty();
    }
}