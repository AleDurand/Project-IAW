package proyect.exceptions;

@SuppressWarnings("serial")
public class EntityAlreadyExistsException extends Exception {
	
	public EntityAlreadyExistsException() {
		super("User already exists.");
	}
}
