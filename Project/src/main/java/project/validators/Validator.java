package project.validators;

import project.exceptions.InvalidIdException;

public class Validator {

    public static int validateId(String id) throws InvalidIdException {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new InvalidIdException();
        }
    }
}
