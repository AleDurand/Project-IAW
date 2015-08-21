package proyect.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import proyect.exceptions.EntityAlreadyExistsException;
import proyect.exceptions.EntityNotFoundException;
import proyect.model.Student;
import proyect.services.StudentService;

@RestController
@RequestMapping(value = "/students")
@Api( value = "/students", description = "Manage people" )
// @PreAuthorize("#username == principal.username")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Student> create(@RequestBody Student student)
			throws EntityAlreadyExistsException {
		Student toReturn = studentService.create(student);
		return new ResponseEntity<Student>(toReturn, HttpStatus.OK);
	}

	// @PreAuthorize("#username == principal.username")
	@RequestMapping(value = "/{lu}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Student> read(@PathVariable Long lu)
			throws EntityNotFoundException {
		Student toReturn = studentService.read(lu);
		return new ResponseEntity<Student>(toReturn, HttpStatus.OK);
	}

	// @PreAuthorize("#username == principal.username")
	@RequestMapping(value = "/{lu}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Student> update(@PathVariable Long lu,
			@RequestBody Student student) throws EntityNotFoundException,
			EntityAlreadyExistsException {
		Student toReturn = studentService.update(lu, student);
		return new ResponseEntity<Student>(toReturn, HttpStatus.OK);
	}

	// @PreAuthorize("#username == principal.username")
	@RequestMapping(value = "/{lu}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long lu)
			throws EntityNotFoundException {
		studentService.delete(lu);
		return new ResponseEntity<String>("Student deleted.", HttpStatus.OK);
	}

	@ApiOperation( 
		    value = "Find person by e-mail", 
		    notes = "Find person by e-mail", 
		    response = Student.class,
		    responseContainer = "List"
		)
		@ApiResponses( {
		    @ApiResponse( code = 404, message = "Person with such e-mail doesn't exists" )    
		} )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Student>> getAll() {
		List<Student> students = studentService.getAll();
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
}
