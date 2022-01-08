package com.sda.weatherprojectbackend.repositories;

import com.sda.weatherprojectbackend.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
