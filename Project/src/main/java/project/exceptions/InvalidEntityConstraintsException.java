package project.exceptions;

public class InvalidEntityConstraintsException extends Exception{

    public InvalidEntityConstraintsException(String entityName, String message){
        super(String.format("%s: Constrains fails. %s", entityName, message));
    }
}
