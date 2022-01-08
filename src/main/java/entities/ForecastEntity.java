package entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "forecast")
public class ForecastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private LocationEntity location;
    private LocalDate forecastDate;
    private LocalDate queryDate;
    @OneToMany(mappedBy = "forecast")
    List<ForecastDetailsEntity> forecastDetails;
}
