package proyect.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyect.exceptions.EntityAlreadyExistsException;
import proyect.exceptions.EntityNotFoundException;
import proyect.model.Student;
import proyect.repositories.StudentRepository;
import proyect.services.StudentService;

@Service
public class StudentServiceImp implements StudentService{
	@Autowired
	private StudentRepository students;
	
	@Override
	public Student create(Student student) throws EntityAlreadyExistsException {
		if(students.findByLu(student.getLu()) != null) throw new EntityAlreadyExistsException();
		return students.save(student);
	}

	@Override
	public Student read(Long lu) throws EntityNotFoundException {
		Student toReturn = students.findByLu(lu);
		if(toReturn == null) throw new EntityNotFoundException();
		return toReturn;
	}

	@Override
	public Student update(Long lu, Student student) throws EntityNotFoundException,
			EntityAlreadyExistsException {
		Student toReturn = students.findByLu(lu);
		if(toReturn == null) throw new EntityNotFoundException();
		return students.save(student);
	}

	@Override
	public void delete(Long lu) throws EntityNotFoundException {
		Student toDelete = students.findByLu(lu);
		if(toDelete == null) throw new EntityNotFoundException();
		students.delete(toDelete.getId());
	}

	@Override
	public List<Student> getAll() {
		return students.findAll();
	}

}
