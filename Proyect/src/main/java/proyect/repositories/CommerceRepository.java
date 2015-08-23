package proyect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyect.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	public Student findByLu(long lu);

}
