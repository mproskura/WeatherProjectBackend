package com.sda.weatherprojectbackend.repositories.jpa;

import com.sda.weatherprojectbackend.entities.ForecastDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastDetailsRepository extends JpaRepository<ForecastDetailsEntity, Long> {
}
