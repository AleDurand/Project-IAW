package project.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable
@Table(name = "geo_location")
@ApiModel(value = "Geolocation", description = "Geolocation for google maps")
public class GeoLocationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "latitude", nullable = false)
    @ApiModelProperty(name = "Latitude", value = "Latitude value for google maps", required = true)
    private Integer latitude;

    @Column(name = "longitude", nullable = false)
    @ApiModelProperty(name = "Longitude", value = "Longitude value for google maps", required = true)
    private Integer longitude;

    public GeoLocationModel() {

    }

    public GeoLocationModel(Integer latitude, Integer longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

}
