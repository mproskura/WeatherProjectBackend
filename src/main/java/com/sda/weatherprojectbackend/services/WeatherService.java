package com.sda.weatherprojectbackend.services;

import com.sda.weatherprojectbackend.components.weather.IWeatherForecastComponent;
import com.sda.weatherprojectbackend.components.weather.OpenWeatherComponent;
import com.sda.weatherprojectbackend.components.weather.YrnoForecastComponent;
import com.sda.weatherprojectbackend.entities.ForecastDetailsEntity;
import com.sda.weatherprojectbackend.mapper.ForecastLocationMapper;
import com.sda.weatherprojectbackend.models.Forecast;
import com.sda.weatherprojectbackend.models.ForecastDetails;
import com.sda.weatherprojectbackend.models.ForecastLocation;
import com.sda.weatherprojectbackend.repositories.ForecastLocationRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService implements IWeatherService {
    //todo jak to ogarnąc, zeby było ladnie (otwarte na rozszerzenia)

    private final ForecastLocationRepository forecastLocationRepository;
    private final ComponentFactory componentFactory;

    public WeatherService(ForecastLocationRepository forecastLocationRepository, ComponentFactory componentFactory) {
        this.forecastLocationRepository = forecastLocationRepository;
        this.componentFactory = componentFactory;
    }

    @Override
    public Forecast getForecast(long locationId, LocalDate forecastDate) {
        ForecastLocation location = ForecastLocationMapper.locationFromEntity(forecastLocationRepository.getById(locationId));
        List<ForecastDetailsEntity> forecastDetails = new ArrayList<>(2);

        Optional<ForecastDetails> detailsOptional = yrnoForecastComponent.getForecast(location, forecastDate);
        if (detailsOptional.isPresent()){
            WeatherService service = new WeatherService("")
           forecastDetails.add(detailsOptional.get());
        }



        return null;
    }


    private void getForecastFromExternalServices(IWeatherForecastComponent weatherForecastComponent){
        weatherForecastComponent.getForecast(loc)

    }
}
