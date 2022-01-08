package repositories;

import entities.LocationSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationSearchRepository extends JpaRepository<LocationSearchEntity, Long> {
}
