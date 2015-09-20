package project.exceptions;

import project.exceptions.messages.DefaultMessage;

public class AssociationAlreadyExistsException extends CustomException {

    public AssociationAlreadyExistsException(String entityName, String entityName2) {
        super(new DefaultMessage(400, String.format("Association between %s and %s already exists.", entityName, entityName2)));
    }
}
