package com.sda.weatherprojectbackend.repositories.position;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weatherprojectbackend.models.ForecastLocation;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Component
public class PositionStackLocationRepository implements ILocationService {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<ForecastLocation> findLocations(String locationQuery) {
        URI uri = new PositionStackUri().get(locationQuery);
        List<ForecastLocation> locations = new ArrayList<>();

        HttpRequest request = null;
        request = HttpRequest
                .newBuilder(uri)
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode results = mapper.readTree(response.body());
            Iterator<JsonNode> elements = results.get("data").elements();
            for (Iterator<JsonNode> it = elements; it.hasNext(); ) {
                JsonNode element = it.next();
                ForecastLocation location = mapper.readValue(element.toString(), ForecastLocation.class);
                locations.add(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return locations;
    }
}
