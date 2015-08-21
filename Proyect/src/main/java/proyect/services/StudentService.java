package proyect.services;

import java.util.List;

import proyect.exceptions.EntityAlreadyExistsException;
import proyect.exceptions.EntityNotFoundException;
import proyect.model.Student;

public interface StudentService {

	public Student create(Student student) throws EntityAlreadyExistsException;
	
	public Student read(Long lu) throws EntityNotFoundException;
	
	public Student update(Long lu, Student student) throws EntityNotFoundException, EntityAlreadyExistsException;
	
	public void delete(Long lu) throws EntityNotFoundException;
	
	public List<Student> getAll();
	
}
