package com.sda.weatherprojectbackend.repositories.jpa;

import com.sda.weatherprojectbackend.entities.LocationSearchEntity;
import com.sda.weatherprojectbackend.models.ForecastLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationSearchRepository extends JpaRepository<LocationSearchEntity, Long> {
    public List<LocationSearchEntity> getAllBySearchString(String searchString);
}
