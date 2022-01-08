package com.sda.weatherprojectbackend.repositories.position;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class PositionStackUri {
    public URI get(String locationQuery) {
        HttpGet httpGet = new HttpGet("http://api.positionstack.com/v1/forward");
        try {
            URI uri = new URIBuilder(httpGet.getURI())
                    .addParameter("access_key", "d3a90060af47c6d23e5e7608ba14467a")
                    .addParameter("query", locationQuery)
                    .build();
            return uri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
