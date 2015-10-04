package project.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.OperationModel;
import project.models.PropertyModel;

@Component
public class PropertyModelValidator {

    @Autowired
    private AddressModelValidator addressValidator;

    @Autowired
    private OperationModelValidator operationValidator;

    public void validateForCreate(PropertyModel property) throws InvalidEntityConstraintsException {
        if(property.getRooms() == null)
            throw new InvalidEntityConstraintsException("Property","Rooms is null");

        if (property.getRooms() < 0)
            throw new InvalidEntityConstraintsException("Property", "Rooms is less than 0.");

        if(property.getSize() == null)
            throw new InvalidEntityConstraintsException("Property","Size is null");

        if (property.getSize() < 0)
            throw new InvalidEntityConstraintsException("Property", "Size is less than 0.");

        if (property.getAddress() == null)
            throw new InvalidEntityConstraintsException("Property", "Address is null.");

        addressValidator.validateForCreate(property.getAddress());

        if (property.getState() == null)
            throw new InvalidEntityConstraintsException("Property", "State is null.");

        if (!property.getState().equals("AVAILABLE") && !property.getState().equals("UNAVAILABLE"))
            throw new InvalidEntityConstraintsException("Property", "Property state must be 'AVAILABLE' or 'UNAVAILABLE'.");

        if (property.getOperation() == null)
            throw new InvalidEntityConstraintsException("Property", "Operation is null.");


        for (OperationModel o : property.getOperation()) {
            operationValidator.validateForCreate(o);
            for (OperationModel o2 : property.getOperation()) {
                if (o != o2 && o.getType().equals(o2.getType())) {
                    throw new InvalidEntityConstraintsException("Property", String.format("The operation %s must be unique", o.getType()));
                }
            }
        }
    }

    public void validateForUpdate(PropertyModel property) throws InvalidEntityConstraintsException {
        if (property.getRooms() != null && property.getRooms() < 0)
            throw new InvalidEntityConstraintsException("Property", "Rooms is less than 0.");

        if (property.getSize() != null && property.getSize() < 0)
            throw new InvalidEntityConstraintsException("Property", "Size is less than 0.");

        if (property.getAddress() != null)
            addressValidator.validateForUpdate(property.getAddress());

        if (property.getState() != null)
            if (!property.getState().equals("AVAILABLE") && !property.getState().equals("UNAVAILABLE"))
                throw new InvalidEntityConstraintsException("Property", "Property state must be 'AVAILABLE' or 'UNAVAILABLE'.");

        if (property.getOperation() != null) {
            for (OperationModel o : property.getOperation()) {
                operationValidator.validateForUpdate(o);
                for (OperationModel o2 : property.getOperation()) {
                    if (o != o2 && o.getType().equals(o2.getType())) {
                        throw new InvalidEntityConstraintsException("Property", String.format("The operation %s must be unique", o.getType()));
                    }
                }
            }
        }
    }
}
