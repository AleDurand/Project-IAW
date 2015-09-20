package project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@ApiModel(value = "Address", description = "Address representation")
public class AddressModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty(name = "Id", value = "Address id", required = false)
    private Integer id;

    @Column(name = "street", nullable = false)
    @ApiModelProperty(name = "Street", value = "Address street", required = true)
    private String street;

    @Column(name = "suite")
    @ApiModelProperty(name = "Suite", value = "Address suite", required = false)
    private String suite;

    @Column(name = "city", nullable = false)
    @ApiModelProperty(name = "City", value = "Address city", required = true)
    private String city;

    @Column(name = "zip_code", nullable = false)
    @ApiModelProperty(name = "Zip Code", value = "Address zip code", required = true)
    private Integer zipCode;

    @Embedded
    private GeoLocationModel geoLocation;

    public AddressModel() {

    }

    public AddressModel(Integer id, String street, String suite, String city, Integer zipCode, GeoLocationModel geoLocation) {
        this.id = id;
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipCode = zipCode;
        this.geoLocation = geoLocation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public GeoLocationModel getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocationModel geoLocation) {
        this.geoLocation = geoLocation;
    }

}
