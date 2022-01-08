package com.sda.weatherprojectbackend.services;

import com.sda.weatherprojectbackend.models.ForecastLocation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LocationServiceTest {

    @Autowired
    LocationService locationService;

    @Test
    void test() {
        List<ForecastLocation> lublin = locationService.getLocations("lublin");
        lublin.forEach(location -> System.out.println(location.toString()));
    }
}