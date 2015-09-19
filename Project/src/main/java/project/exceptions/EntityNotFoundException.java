package project.exceptions;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String entityName, int id) {
        super(String.format("%s: %d has not been found.", entityName, id));
    }
}
