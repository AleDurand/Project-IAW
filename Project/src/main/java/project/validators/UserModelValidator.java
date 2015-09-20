package project.validators;

import org.springframework.stereotype.Component;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.UserModel;

import java.util.regex.Pattern;

@Component
public class UserModelValidator {

    private static final String PASSWORD_PATTERN = "^[A-z0-9]{6,}$";

    public void validateForCreate(UserModel user) throws InvalidEntityConstraintsException {
        if (user.getUsername() == null || user.getUsername().isEmpty())
            throw new InvalidEntityConstraintsException("User", "Username is null or empty.");

        if (user.getPassword() == null || user.getPassword().isEmpty())
            throw new InvalidEntityConstraintsException("User", "Password is null or empty.");

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (user.getPassword() == null || !pattern.matcher(user.getPassword()).matches())
            throw new InvalidEntityConstraintsException("User", "Invalid password format.");
    }

    public void validateForUpdate(UserModel user) throws InvalidEntityConstraintsException {
        if (user.getUsername() != null && user.getUsername().isEmpty())
            throw new InvalidEntityConstraintsException("User", "Username is null or empty.");

        if (user.getPassword() != null && user.getPassword().isEmpty())
            throw new InvalidEntityConstraintsException("User", "Password is null or empty.");

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (!pattern.matcher(user.getPassword()).matches())
            throw new InvalidEntityConstraintsException("User", "Invalid password format.");
    }
}
