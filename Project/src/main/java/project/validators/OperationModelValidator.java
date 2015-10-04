package project.validators;


import org.springframework.stereotype.Component;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.OperationModel;

@Component
public class OperationModelValidator {

    public void validateForCreate(OperationModel operationModel) throws InvalidEntityConstraintsException {
        if (!operationModel.getType().equals("FOR_RENT") && !operationModel.getType().equals("FOR_SALE"))
            throw new InvalidEntityConstraintsException("Operation", "Operation type must be 'FOR_RENT' or 'FOR_SALE'.");

        if (operationModel.getPrice() != null && operationModel.getPrice() < 0)
            throw new InvalidEntityConstraintsException("Operation", "Price is less than 0.");
    }

    public void validateForUpdate(OperationModel operationModel) throws InvalidEntityConstraintsException {
        if (!operationModel.getType().equals("FOR_RENT") && !operationModel.getType().equals("FOR_SALE"))
            throw new InvalidEntityConstraintsException("Operation", "Operation type must be 'FOR_RENT' or 'FOR_SALE'.");

        if (operationModel.getPrice() != null && operationModel.getPrice() < 0)
            throw new InvalidEntityConstraintsException("Operation", "Price is less than 0.");
    }
}
