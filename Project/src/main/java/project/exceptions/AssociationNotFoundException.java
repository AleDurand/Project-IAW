package project.exceptions;

import project.exceptions.messages.DefaultMessage;

public class AssociationNotFoundException extends CustomException {

    public AssociationNotFoundException(String entityName, String entityName2) {
        super(new DefaultMessage(404, String.format("Association between %s and %s has not been found.", entityName, entityName2)));
    }
}
