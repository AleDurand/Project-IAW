package project.exceptions;

public class InvalidIdException extends Exception {

    public InvalidIdException(){
        super("Invalid id. An integer was expected.");
    }
}
