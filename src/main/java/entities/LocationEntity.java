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
@Table(name = "location")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitude;
    String type;
    String name;
    String number;
    @Column(name = "postal_code")
    String postalCode;
    String street;
    String confidence;
    String region;
    @Column(name = "region_code")
    String regionCode;
    String county;
    String locality;
    @Column(name = "administrative_area")
    String administrativeArea;
    String neighbourhood;
    String country;
    @Column(name = "country_code")
    String countryCode;
    String continent;
    String label;
    @OneToMany(mappedBy = "location")
    private List<LocationSearchEntity> locationSearches;
    @OneToMany(mappedBy = "location")
    private List<ForecastEntity> forecasts;
}
