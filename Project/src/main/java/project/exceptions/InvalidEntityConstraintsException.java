package project.exceptions;

import project.exceptions.messages.DefaultMessage;

public class InvalidEntityConstraintsException extends CustomException {

    public InvalidEntityConstraintsException(String entityName, String message) {
        super(new DefaultMessage(400, String.format("%s: Constrains fails. %s", entityName, message)));
    }
}
