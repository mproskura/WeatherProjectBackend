package com.sda.weatherprojectbackend.repositories.jpa;

import com.sda.weatherprojectbackend.entities.ForecastLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastLocationRepository extends JpaRepository<ForecastLocationEntity, Long> {
}
