package project.validators;

import org.springframework.stereotype.Component;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.RealEstateAgentModel;

import java.util.regex.Pattern;

@Component
public class RealEstateAgentModelValidator {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public void validateForCreate(RealEstateAgentModel realEstateAgent) throws InvalidEntityConstraintsException {
        if (realEstateAgent.getName() == null || realEstateAgent.getName().isEmpty())
            throw new InvalidEntityConstraintsException("RealEstateAgent", "Name is null or empty.");

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (realEstateAgent.getEmail() != null && !pattern.matcher(realEstateAgent.getEmail()).matches())
            throw new InvalidEntityConstraintsException("RealEstateAgent", "Invalid email format.");
    }

    public void validateForUpdate(RealEstateAgentModel realEstateAgent) throws InvalidEntityConstraintsException {
        if (realEstateAgent.getName() != null && realEstateAgent.getName().isEmpty())
            throw new InvalidEntityConstraintsException("RealEstateAgent", "Name is null or empty.");

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (realEstateAgent.getEmail() != null && !pattern.matcher(realEstateAgent.getEmail()).matches())
            throw new InvalidEntityConstraintsException("RealEstateAgent", "Invalid email format.");
    }
}
