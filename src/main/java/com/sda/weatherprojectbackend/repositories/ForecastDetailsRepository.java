package com.sda.weatherprojectbackend.repositories;

import com.sda.weatherprojectbackend.entities.ForecastDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastDetailsRepository extends JpaRepository<ForecastDetailsEntity, Long> {
}
