package com.sda.weatherprojectbackend.repositories;

import com.sda.weatherprojectbackend.entities.WeatherSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<WeatherSourceEntity, Long> {
}
