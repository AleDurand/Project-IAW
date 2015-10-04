package project.exceptions;

import project.exceptions.messages.DefaultMessage;

public class EntityNotFoundException extends CustomException {

    public EntityNotFoundException(String entityName, int id) {
        super(new DefaultMessage(404, String.format("%s: %d has not been found.", entityName, id)));
    }
}
