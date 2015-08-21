package proyect.services;

import proyect.exceptions.EntityAlreadyExistsException;
import proyect.exceptions.EntityNotFoundException;
import proyect.model.Teacher;

public interface TeacherService {

	public Teacher create(Teacher teacher) throws EntityAlreadyExistsException;
	
	public Teacher read(String username) throws EntityNotFoundException;
	
	public Teacher update(Teacher teacher) throws EntityNotFoundException, EntityAlreadyExistsException;
	
	public void delete(String username) throws EntityNotFoundException;
	
}
