package com.sda.weatherprojectbackend.components.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weatherprojectbackend.components.uri.TomorrowIoUri;
import com.sda.weatherprojectbackend.components.uri.YrnoUri;
import com.sda.weatherprojectbackend.models.ForecastDetails;
import com.sda.weatherprojectbackend.models.ForecastLocation;

import java.net.URI;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Optional;


public class TomorrowIoComponent implements IWeatherForecastComponent {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Optional<ForecastDetails> getForecast(ForecastLocation location, LocalDate forecastDate) {

        Optional<URI> optionalURI = new TomorrowIoUri().get(location);
        if (optionalURI.isEmpty()) {
            return Optional.empty();
        }

        URI uri = optionalURI.get();

        Optional<HttpResponse<String>> responseOptional = HttpRequest.getResponse(uri);
        if (responseOptional.isPresent()) {
            HttpResponse<String> response = responseOptional.get();
            try {
                if (response.statusCode() == 429) {
                    System.out.println("Number of queries for Tomorrow.io exceeded");
                    return Optional.empty();
                }

                JsonNode results = mapper.readTree(response.body());
                JsonNode timelines = results.get("data").get("timelines");
                JsonNode intervals = timelines.get(0).get("intervals");

                Iterator<JsonNode> elements = intervals.elements();

                LocalDateTime forecastDateTime = ForecastDateTime.getForecastDateTime(forecastDate);

                for (Iterator<JsonNode> it = elements; it.hasNext(); ) {
                    JsonNode element = it.next();
                    String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                    LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(element.get("startTime").toString().replace("\"", "")));

                    if (localDateTime.equals(forecastDateTime)) {
                        JsonNode forecast = element.get("values");
                        ForecastDetails weatherForecast = ForecastDetails.builder()
                                .airPressure(convertPressureToHPa(Double.valueOf(forecast.get("pressureSurfaceLevel").toString())))
                                .airTemperature(Double.valueOf(forecast.get("temperature").toString()))
                                .cloudiness(Double.valueOf(forecast.get("cloudCover").toString()))
                                .humidity(Double.valueOf(forecast.get("humidity").toString()))
                                .windDirection(Double.valueOf(forecast.get("windDirection").toString()))
                                .windSpeed(Double.valueOf(forecast.get("windSpeed").toString()))
                                .build();
                        return Optional.of(weatherForecast);
                    }
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
//            catch (NullPointerException npe){
//                npe.printStackTrace();
//            }
        }
        return Optional.empty();
    }

    private double convertPressureToHPa(double pressureMmHg) {
        return Math.round(pressureMmHg * 1.33322);
    }
}