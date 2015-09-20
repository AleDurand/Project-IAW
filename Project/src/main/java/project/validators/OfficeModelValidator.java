package project.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.OfficeModel;

import java.util.regex.Pattern;

@Component
public class OfficeModelValidator {

    @Autowired
    private AddressModelValidator addressValidator;

    private static final String PHONE_PATTERN = "^\\+[0-9]{1,3}\\-[0-9]{2,14}\\-[0-9]{4,14}$";

    public void validateForCreate(OfficeModel office) throws InvalidEntityConstraintsException {
        if (office.getName() == null || office.getName().isEmpty())
            throw new InvalidEntityConstraintsException("Office", "Name is null or empty");

        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        if (office.getPhone() != null && !pattern.matcher(office.getPhone()).matches())
            throw new InvalidEntityConstraintsException("Office", "Invalid phone format.");

        if (office.getAddress() != null)
            addressValidator.validateForCreate(office.getAddress());

    }

    public void validateForUpdate(OfficeModel office) throws InvalidEntityConstraintsException {
        if (office.getName() != null && office.getName().isEmpty())
            throw new InvalidEntityConstraintsException("Office", "Name is null or empty");

        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        if (office.getPhone() != null && !pattern.matcher(office.getPhone()).matches())
            throw new InvalidEntityConstraintsException("Office", "Invalid phone format.");

        if (office.getAddress() != null)
            addressValidator.validateForUpdate(office.getAddress());

    }
}
