package com.sda.weatherprojectbackend.services;

import com.sda.weatherprojectbackend.components.weather.IWeatherForecastComponent;
import com.sda.weatherprojectbackend.entities.ForecastDetailsEntity;
import com.sda.weatherprojectbackend.entities.ForecastEntity;
import com.sda.weatherprojectbackend.mapper.ForecastLocationMapper;
import com.sda.weatherprojectbackend.mapper.ForecastMapper;
import com.sda.weatherprojectbackend.mapper.WeatherSourceMapper;
import com.sda.weatherprojectbackend.models.Forecast;
import com.sda.weatherprojectbackend.models.ForecastDetails;
import com.sda.weatherprojectbackend.models.ForecastLocation;
import com.sda.weatherprojectbackend.models.WeatherSource;
import com.sda.weatherprojectbackend.repositories.ForecastLocationRepository;
import com.sda.weatherprojectbackend.repositories.ForecastRepository;
import com.sda.weatherprojectbackend.repositories.WeatherSourceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherService implements IWeatherService {

    private final ForecastLocationRepository forecastLocationRepository;
    private final ComponentFactory componentFactory;
    private final WeatherSourceRepository weatherSourceRepository;
    private final ForecastRepository forecastRepository;

    public WeatherService(ForecastLocationRepository forecastLocationRepository, ComponentFactory componentFactory, WeatherSourceRepository weatherSourceRepository, ForecastRepository forecastRepository) {
        this.forecastLocationRepository = forecastLocationRepository;
        this.componentFactory = componentFactory;
        this.weatherSourceRepository = weatherSourceRepository;
        this.forecastRepository = forecastRepository;
    }

    @Override
    public Forecast getForecast(long locationId, LocalDate forecastDate) {
        ForecastLocation location = ForecastLocationMapper.locationFromEntity(forecastLocationRepository.getById(locationId));
        final ForecastEntity localForecastResult = forecastRepository
                .findByQueryDateAndForecastDateAndAndLocation(LocalDate.now(), forecastDate, ForecastLocationMapper.entityFromLocation(location));

        if (localForecastResult != null) {
            return ForecastMapper.forecastFromEntity(localForecastResult);
        } else {
            return getForecastFromRemoteComponents(location, forecastDate);
        }
    }

    private Forecast getForecastFromRemoteComponents(ForecastLocation location, LocalDate forecastDate) {
        List<ForecastDetails> forecastDetails = new ArrayList<>(2);
        List<WeatherSource> weatherSources = weatherSourceRepository.findAll().stream().map(WeatherSourceMapper::sourceFromEntity).collect(Collectors.toList());
        Forecast forecast = Forecast.builder()
                .forecastDate(LocalDate.now())
                .queryDate(LocalDate.now())
                .location(location)
                .build();

        for (WeatherSource weatherSource : weatherSources) {
            Optional<IWeatherForecastComponent> forecastComponentOptional = ComponentFactory.get(weatherSource.getSourceName());
            if (forecastComponentOptional.isPresent()) {
                Optional<ForecastDetails> optionalForecastDetails = forecastComponentOptional.get().getForecast(location, forecastDate);
                if (optionalForecastDetails.isPresent()) {
                    forecastDetails.add(optionalForecastDetails.get());
                }
            }
        }
        forecast.setForecastDetails(forecastDetails);
        ForecastEntity save = forecastRepository.save(ForecastMapper.entityFromForecast(forecast));
        return ForecastMapper.forecastFromEntity(save);
    }
}
