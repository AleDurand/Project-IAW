package project.validators;

import org.springframework.stereotype.Component;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.AddressModel;

@Component
public class AddressModelValidator {

    public void validateForCreate(AddressModel address) throws InvalidEntityConstraintsException {
        if (address.getStreet() == null || address.getStreet().isEmpty())
            throw new InvalidEntityConstraintsException("Address", "Street is null or empty.");

        if (address.getCity() == null || address.getCity().isEmpty())
            throw new InvalidEntityConstraintsException("Address", "City is null or empty.");

        if (address.getZipCode() == null)
            throw new InvalidEntityConstraintsException("Address", "ZipCode is null.");

        if (address.getZipCode() < 0)
            throw new InvalidEntityConstraintsException("Address", "ZipCode is less than 0.");

        if (address.getGeoLocation() == null)
            throw new InvalidEntityConstraintsException("Address", "Geolocation is null.");

        if (address.getGeoLocation().getLatitude() == null || address.getGeoLocation().getLongitude() == null)
            throw new InvalidEntityConstraintsException("Address", "Geolocation is not complete.");
    }

    public void validateForUpdate(AddressModel address) throws InvalidEntityConstraintsException {
        if (address.getStreet() != null && address.getStreet().isEmpty())
            throw new InvalidEntityConstraintsException("Address", "Street null or is empty.");

        if (address.getCity() != null && address.getCity().isEmpty())
            throw new InvalidEntityConstraintsException("Address", "City null or is empty.");

        if (address.getZipCode() != null && address.getZipCode() < 0)
            throw new InvalidEntityConstraintsException("Address", "ZipCode is less than 0.");

        if (address.getGeoLocation() != null)
            if (address.getGeoLocation().getLatitude() == null || address.getGeoLocation().getLongitude() == null)
                throw new InvalidEntityConstraintsException("Address", "Geolocation is not complete.");
    }
}
