package proyect.services;

import proyect.exceptions.EntityAlreadyExistsException;
import proyect.exceptions.EntityNotFoundException;
import proyect.model.User;

public interface UserService {

	public User create(User user) throws EntityAlreadyExistsException;
	
	public User read(String username) throws EntityNotFoundException;
	
	public User update(User user) throws EntityNotFoundException, EntityAlreadyExistsException;
	
	public void delete(String username) throws EntityNotFoundException;
	
}
