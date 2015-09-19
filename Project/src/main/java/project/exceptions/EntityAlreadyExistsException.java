package project.exceptions;

public class EntityAlreadyExistsException extends Exception {

    public EntityAlreadyExistsException(String entityName, String fieldName, String fieldValue) {
        super(String.format("%s: %s %s already exists.", entityName, fieldName, fieldValue));
    }
}
