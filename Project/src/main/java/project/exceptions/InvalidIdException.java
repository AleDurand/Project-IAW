package project.exceptions;

import project.exceptions.messages.DefaultMessage;

public class InvalidIdException extends CustomException {

    public InvalidIdException() {
        super(new DefaultMessage(400, "Invalid id. An integer was expected."));
    }
}
