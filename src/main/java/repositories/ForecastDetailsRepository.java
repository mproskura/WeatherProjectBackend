package repositories;

import entities.ForecastDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastDetailsRepository extends JpaRepository<ForecastDetailsEntity, Long> {
}
