package project.exceptions;

import project.exceptions.messages.DefaultMessage;

public class CustomException extends RuntimeException {
    private DefaultMessage errorMessage;

    public CustomException(DefaultMessage errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public DefaultMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(DefaultMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
