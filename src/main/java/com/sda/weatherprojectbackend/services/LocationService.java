package com.sda.weatherprojectbackend.services;


import com.sda.weatherprojectbackend.entities.ForecastLocationEntity;
import com.sda.weatherprojectbackend.entities.LocationSearchEntity;
import com.sda.weatherprojectbackend.mapper.ForecastLocationMapper;
import com.sda.weatherprojectbackend.models.ForecastLocation;
import com.sda.weatherprojectbackend.repositories.jpa.ForecastLocationRepository;
import org.springframework.stereotype.Service;
import com.sda.weatherprojectbackend.repositories.jpa.LocationSearchRepository;
import com.sda.weatherprojectbackend.repositories.position.PositionStackLocationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService implements ILocationService {

    private LocationSearchRepository localSearchRepository;
    private PositionStackLocationRepository remoteSearchRepository;
    private ForecastLocationRepository locationRepository;

    public LocationService(LocationSearchRepository locationSearchRepository, PositionStackLocationRepository positionStackLocationRepository, ForecastLocationRepository locationRepository) {
        this.localSearchRepository = locationSearchRepository;
        this.remoteSearchRepository = positionStackLocationRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<ForecastLocation> getLocations(String searchString) {
        List<LocationSearchEntity> localSearchLocationResults = localSearchRepository.getAllBySearchString(searchString);

        if (localSearchLocationResults.isEmpty()) {
            addToLocalRepositoryFromRemote(searchString);
        }
        return getLocationsFromLocalRepository(localSearchLocationResults);
    }


    private List<ForecastLocation> getLocationsFromLocalRepository(List<LocationSearchEntity> localSearchLocationResults) {
        List<ForecastLocation> result = new ArrayList<>();
        for (LocationSearchEntity locationSearchEntity : localSearchLocationResults
        ) {
            Optional<ForecastLocationEntity> optionalForecastLocationEntity = locationRepository.findById(locationSearchEntity.getId());
            if (optionalForecastLocationEntity.isPresent()){
                result.add(ForecastLocationMapper.locationFromEntity(optionalForecastLocationEntity.get()));
            }
        }
        return result;
    }

    private void addToLocalRepositoryFromRemote(String searchString) {
        List<ForecastLocation> locationsFromRemoteRepository = remoteSearchRepository.findLocations(searchString);

        for (ForecastLocation location : locationsFromRemoteRepository) {
            ForecastLocationEntity locationEntity = locationRepository.save(ForecastLocationMapper.entityFromLocation(location));

            LocationSearchEntity locationSearchEntity = LocationSearchEntity.builder()
                    .searchString(searchString)
                    .location(locationEntity)
                    .build();
            localSearchRepository.save(locationSearchEntity);
        }
    }
}