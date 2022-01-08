package entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "service")
public class ServiceEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="service_name")
    private String serviceName;
    @OneToMany(mappedBy = "service")
    @Column(name="forecast_details_id")
    private List<ForecastDetailsEntity> forecastDetails;
}
