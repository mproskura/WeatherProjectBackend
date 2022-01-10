package com.sda.weatherprojectbackend.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "location_search")
public class LocationSearchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="search_string")
    private String searchString;
    @ManyToOne
//    @JoinColumn(name="location_id")
    private ForecastLocationEntity location;
}
