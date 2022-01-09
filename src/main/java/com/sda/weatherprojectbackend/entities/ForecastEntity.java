package com.sda.weatherprojectbackend.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;

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
    private ForecastLocationEntity location;
    private LocalDate forecastDate;
    private LocalDate queryDate;
    @OneToMany(mappedBy = "forecast", fetch = FetchType.EAGER)
    List<ForecastDetailsEntity> forecastDetails;
}
