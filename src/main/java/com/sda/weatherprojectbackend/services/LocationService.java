package com.sda.weatherprojectbackend.services;


import com.sda.weatherprojectbackend.entities.ForecastLocationEntity;
import com.sda.weatherprojectbackend.entities.LocationSearchEntity;
import com.sda.weatherprojectbackend.mapper.ForecastLocationMapper;
import com.sda.weatherprojectbackend.models.ForecastLocation;
import com.sda.weatherprojectbackend.repositories.ForecastLocationRepository;
import org.springframework.stereotype.Service;
import com.sda.weatherprojectbackend.repositories.LocationSearchRepository;
import com.sda.weatherprojectbackend.components.position.PositionStackLocationComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService implements ILocationService {

    private LocationSearchRepository localSearchRepository;
    private PositionStackLocationComponent remoteSearchRepository;
    private ForecastLocationRepository locationRepository;

    public LocationService(LocationSearchRepository locationSearchRepository, PositionStackLocationComponent positionStackLocationRepository, ForecastLocationRepository locationRepository) {
        this.localSearchRepository = locationSearchRepository;
        this.remoteSearchRepository = positionStackLocationRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<ForecastLocation> getLocations(String searchString) {
        List<LocationSearchEntity> locationSearchResults = localSearchRepository.getAllBySearchString(searchString);

        if (locationSearchResults.isEmpty()) {
            return addToLocalRepositoryFromRemote(searchString);
        } else {
            return getLocationsFromLocalRepository(locationSearchResults);
        }
    }

    private List<ForecastLocation> getLocationsFromLocalRepository(List<LocationSearchEntity> localSearchLocationResults) {
        List<ForecastLocation> result = new ArrayList<>();
        for (LocationSearchEntity locationSearchEntity : localSearchLocationResults
        ) {
            Optional<ForecastLocationEntity> optionalForecastLocationEntity = locationRepository.findById(locationSearchEntity.getId());
            if (optionalForecastLocationEntity.isPresent()) {
                result.add(ForecastLocationMapper.locationFromEntity(optionalForecastLocationEntity.get()));
            }
        }
        return result;
    }

    private List<ForecastLocation> addToLocalRepositoryFromRemote(String searchString) {
        List<ForecastLocation> locationsFromRemoteRepository = remoteSearchRepository.findLocations(searchString);
        List<ForecastLocation> savedLocations = new ArrayList<>();

        for (ForecastLocation location : locationsFromRemoteRepository) {
            ForecastLocationEntity locationEntity = locationRepository.save(ForecastLocationMapper.entityFromLocation(location));
            savedLocations.add(ForecastLocationMapper.locationFromEntity(locationEntity));

            LocationSearchEntity locationSearchEntity = LocationSearchEntity.builder()
                    .searchString(searchString)
                    .location(locationEntity)
                    .build();
            localSearchRepository.save(locationSearchEntity);
        }
        return savedLocations;
    }
}
