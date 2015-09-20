package project.validators;

import org.springframework.stereotype.Component;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.UserModel;

@Component
public class UserModelValidator {

    public void validateForCreate(UserModel user) throws InvalidEntityConstraintsException {
        if (user.getUsername() == null || user.getUsername().isEmpty())
            throw new InvalidEntityConstraintsException("User", "Username is empty.");

        if (user.getPassword() == null || user.getPassword().isEmpty())
            throw new InvalidEntityConstraintsException("User", "Password is empty.");

        if (user.getPassword().length() < 6)
            throw new InvalidEntityConstraintsException("User", "Password length is less than 6 characters.");
    }

    public void validateForUpdate(UserModel user) throws InvalidEntityConstraintsException {
        if (user.getUsername() != null && user.getUsername().isEmpty())
            throw new InvalidEntityConstraintsException("User", "Username is empty.");

        if (user.getPassword() != null && user.getPassword().isEmpty())
            throw new InvalidEntityConstraintsException("User", "Password is empty.");

        if (user.getPassword() != null && user.getPassword().length() < 6)
            throw new InvalidEntityConstraintsException("User", "Password length is less than 6 characters.");
    }
}
