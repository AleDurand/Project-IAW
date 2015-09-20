package project.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.PropertyModel;

@Component
public class PropertyModelValidator {

    @Autowired
    private AddressModelValidator addressValidator;

    public void validateForCreate(PropertyModel property) throws InvalidEntityConstraintsException {
        if (property.getRooms() != null && property.getRooms() < 0)
            throw new InvalidEntityConstraintsException("Property", "Rooms is less than 0.");

        if (property.getSize() != null && property.getSize() < 0)
            throw new InvalidEntityConstraintsException("Property", "Size is less than 0.");

        if (property.getAddress() != null)
            addressValidator.validateForCreate(property.getAddress());

        //TODO Add validator for operation field
    }

    public void validateForUpdate(PropertyModel property) throws InvalidEntityConstraintsException {
        if (property.getRooms() != null && property.getRooms() < 0)
            throw new InvalidEntityConstraintsException("Property", "Rooms is less than 0.");

        if (property.getSize() != null && property.getSize() < 0)
            throw new InvalidEntityConstraintsException("Property", "Size is less than 0.");

        if (property.getAddress() != null)
            addressValidator.validateForCreate(property.getAddress());

        //TODO Add validator for operation field
    }
}
