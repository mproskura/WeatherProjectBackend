package com.sda.weatherprojectbackend.components.weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Optional;

public class HttpRequest {

    public static Optional<HttpResponse<String>> getResponse(URI uri){
        java.net.http.HttpRequest request = null;
        request = java.net.http.HttpRequest
                .newBuilder(uri)
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.of(response);
    }
}
