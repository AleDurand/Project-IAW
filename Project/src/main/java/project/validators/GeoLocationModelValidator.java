package project.validators;


import org.springframework.stereotype.Component;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.GeoLocationModel;

@Component
public class GeoLocationModelValidator {

    public void validateForCreate(GeoLocationModel geoLocation) throws InvalidEntityConstraintsException {
        if (geoLocation.getLatitude() == null || geoLocation.getLongitude() == null)
            throw new InvalidEntityConstraintsException("Geolocation", "Geolocation is not complete.");
    }

    public void validateForUpdate(GeoLocationModel geoLocation) throws InvalidEntityConstraintsException {
        if (geoLocation.getLatitude() == null || geoLocation.getLongitude() == null)
            throw new InvalidEntityConstraintsException("Geolocation", "Geolocation is not complete.");
    }

}
