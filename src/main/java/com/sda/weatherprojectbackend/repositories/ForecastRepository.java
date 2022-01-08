package com.sda.weatherprojectbackend.repositories;

import com.sda.weatherprojectbackend.entities.ForecastEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastRepository extends JpaRepository<ForecastEntity, Long> {
}
