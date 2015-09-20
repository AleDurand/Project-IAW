package project.validators;

import org.springframework.stereotype.Component;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.RealStateAgentModel;

import java.util.regex.Pattern;

@Component
public class RealStateAgentModelValidator {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public void validateForCreate(RealStateAgentModel realStateAgent) throws InvalidEntityConstraintsException {
        if (realStateAgent.getName() == null || realStateAgent.getName().isEmpty())
            throw new InvalidEntityConstraintsException("RealStateAgent", "Name is null or empty.");

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (realStateAgent.getEmail() != null && !pattern.matcher(realStateAgent.getEmail()).matches())
            throw new InvalidEntityConstraintsException("RealStateAgent", "Invalid email format.");
    }

    public void validateForUpdate(RealStateAgentModel realStateAgent) throws InvalidEntityConstraintsException {
        if (realStateAgent.getName() != null && realStateAgent.getName().isEmpty())
            throw new InvalidEntityConstraintsException("RealStateAgent", "Name is null or empty.");

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (realStateAgent.getEmail() != null && !pattern.matcher(realStateAgent.getEmail()).matches())
            throw new InvalidEntityConstraintsException("RealStateAgent", "Invalid email format.");
    }
}
