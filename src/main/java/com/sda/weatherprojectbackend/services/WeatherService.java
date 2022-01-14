package com.sda.weatherprojectbackend.services;

import com.sda.weatherprojectbackend.components.weather.IWeatherForecastComponent;
import com.sda.weatherprojectbackend.entities.ForecastDetailsEntity;
import com.sda.weatherprojectbackend.entities.ForecastEntity;
import com.sda.weatherprojectbackend.entities.ForecastLocationEntity;
import com.sda.weatherprojectbackend.mapper.ForecastDetailsMapper;
import com.sda.weatherprojectbackend.mapper.ForecastLocationMapper;
import com.sda.weatherprojectbackend.mapper.ForecastMapper;
import com.sda.weatherprojectbackend.mapper.WeatherSourceMapper;
import com.sda.weatherprojectbackend.models.Forecast;
import com.sda.weatherprojectbackend.models.ForecastDetails;
import com.sda.weatherprojectbackend.models.ForecastLocation;
import com.sda.weatherprojectbackend.models.WeatherSource;
import com.sda.weatherprojectbackend.repositories.ForecastDetailsRepository;
import com.sda.weatherprojectbackend.repositories.ForecastLocationRepository;
import com.sda.weatherprojectbackend.repositories.ForecastRepository;
import com.sda.weatherprojectbackend.repositories.WeatherSourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WeatherService implements IWeatherService {

    private final ForecastLocationRepository forecastLocationRepository;
    private final ComponentFactory componentFactory;
    private final WeatherSourceRepository weatherSourceRepository;
    private final ForecastRepository forecastRepository;
    private final ForecastDetailsRepository forecastDetailsRepository;

    public WeatherService(ForecastLocationRepository forecastLocationRepository, ComponentFactory componentFactory, WeatherSourceRepository weatherSourceRepository, ForecastRepository forecastRepository, ForecastDetailsRepository forecastDetailsRepository) {
        this.forecastLocationRepository = forecastLocationRepository;
        this.componentFactory = componentFactory;
        this.weatherSourceRepository = weatherSourceRepository;
        this.forecastRepository = forecastRepository;
        this.forecastDetailsRepository = forecastDetailsRepository;
    }

    @Override
    public Forecast getForecast(long locationId, LocalDate forecastDate) {
        ForecastLocationEntity forecastLocationEntity = forecastLocationRepository.getById(locationId);
        ForecastLocation location = ForecastLocationMapper.locationFromEntity(forecastLocationEntity);
        final ForecastEntity localForecastResult = forecastRepository
                .findByQueryDateAndForecastDateAndAndLocation(LocalDate.now(), forecastDate, forecastLocationEntity);

        if (localForecastResult != null) {
            return ForecastMapper.forecastFromEntity(localForecastResult);
        } else {
            return getForecastFromRemoteComponents(location, forecastDate);
        }
    }

    private Forecast getForecastFromRemoteComponents(ForecastLocation location, LocalDate forecastDate) {
        List<WeatherSource> weatherSources = weatherSourceRepository.findAll().stream().map(WeatherSourceMapper::sourceFromEntity).collect(Collectors.toList());
        Forecast forecast = Forecast.builder()
                .forecastDate(forecastDate)
                .queryDate(LocalDate.now())
                .location(location)
                .forecastDetails(new HashSet<>())
                .build();

        ForecastEntity forecastEntity = saveForcecastDetails(location, forecastDate, forecast, weatherSources);

        return ForecastMapper.forecastFromEntity(forecastEntity);
    }

    private ForecastEntity saveForcecastDetails(ForecastLocation location, LocalDate forecastDate ,Forecast forecast, List<WeatherSource> weatherSources) {
        ForecastEntity forecastEntity = forecastRepository.save(ForecastMapper.entityFromForecast(forecast));

        for (WeatherSource weatherSource : weatherSources) {
            Optional<IWeatherForecastComponent> forecastComponentOptional = ComponentFactory.get(weatherSource.getSourceName());
            if (forecastComponentOptional.isPresent()) {
                IWeatherForecastComponent iWeatherForecastComponent = forecastComponentOptional.get();
                Optional<ForecastDetails> optionalForecastDetails = iWeatherForecastComponent.getForecast(location, forecastDate);
                if (optionalForecastDetails.isPresent()) {
                    ForecastDetails forecastDetails = optionalForecastDetails.get();
                    forecastDetails.setWeatherSource(weatherSource);
                    ForecastDetailsEntity forecastDetailsEntity = ForecastDetailsMapper.entityFromForecastDetails(forecastDetails);
                    forecastDetailsEntity.setForecast(forecastEntity);
                    forecastDetailsRepository.save(forecastDetailsEntity);
                    forecastDetailsRepository.flush();

                    forecastEntity.getForecastDetails().add(forecastDetailsEntity);
                }
            }
        }
        return forecastEntity;
    }
}
