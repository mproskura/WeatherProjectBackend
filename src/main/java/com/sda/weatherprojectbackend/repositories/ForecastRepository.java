package com.sda.weatherprojectbackend.repositories;

import com.sda.weatherprojectbackend.entities.ForecastEntity;
import com.sda.weatherprojectbackend.entities.ForecastLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ForecastRepository extends JpaRepository<ForecastEntity, Long> {
    public ForecastEntity findByQueryDateAndForecastDateAndAndLocation(LocalDate queryDate, LocalDate forecastDate, ForecastLocationEntity forecastLocationEntity);
}
