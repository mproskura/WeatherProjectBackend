package com.sda.weatherprojectbackend.repositories.jpa;

import com.sda.weatherprojectbackend.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
