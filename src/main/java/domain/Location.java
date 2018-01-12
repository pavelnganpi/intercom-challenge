package domain;

import lombok.Data;

@Data
public class Location {
    Double lat;
    Double lgt;

    public Location(Double lat, Double lgt) {
        this.lat = lat;
        this.lgt = lgt;
    }
}
