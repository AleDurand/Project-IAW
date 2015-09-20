package project.exceptions;

import project.exceptions.messages.DefaultMessage;

public class EntityAlreadyExistsException extends CustomException {

    public EntityAlreadyExistsException(String entityName, String fieldName, String fieldValue) {
        super(new DefaultMessage(400, String.format("%s: %s %s already exists.", entityName, fieldName, fieldValue)));
    }
}
