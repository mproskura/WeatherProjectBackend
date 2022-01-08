package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private Long id;
    private double latitude;
    private double longitude;
    String type;
    String name;
    String number;
    String postalCode;
    String street;
    String confidence;
    String region;
    String regionCode;
    String county;
    String locality;
    String administrativeArea;
    String neighbourhood;
    String country;
    String countryCode;
    String continent;
    String label;
}
