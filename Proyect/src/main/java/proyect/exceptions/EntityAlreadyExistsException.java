package proyect.exceptions;

@SuppressWarnings("serial")
public class EntityAlreadyExistsException extends Exception {
	
	public EntityAlreadyExistsException() {
		super("Entity already exists.");
	}
}
