package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.model.Office;
import project.services.OfficeService;

@RestController
@RequestMapping(value = "/offices")
@Api( value = "/offices", description = "Manage offices" )
// @PreAuthorize("#username == principal.username")
public class OfficeController {
	@Autowired
	private OfficeService officeService;
	
	
	@ApiOperation( 
		    value = "Create office", 
		    notes = "nothing", 
		    response = Office.class
		)
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Office> create(@RequestBody Office office){
		Office toReturn = officeService.create(office);
		return new ResponseEntity<Office>(toReturn, HttpStatus.OK);
	}

	// @PreAuthorize("#username == principal.username")
	@ApiOperation( 
		    value = "Get office by id", 
		    notes = "nothing", 
		    response = Office.class
		)
	@ApiResponses( {
	    @ApiResponse( code = 404, message = "Office not exists." )    
	} )
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Office> read(@PathVariable Long id)
			throws EntityNotFoundException {
		Office toReturn = officeService.read(id);
		return new ResponseEntity<Office>(toReturn, HttpStatus.OK);
	}

	// @PreAuthorize("#username == principal.username")
	@ApiOperation( 
		    value = "Update office", 
		    notes = "nothing", 
		    response = Office.class
		)
	@ApiResponses( {
	    @ApiResponse( code = 404, message = "Office not exists." ),
	    @ApiResponse( code = 400, message = "Office already exists." )    
	} )
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Office> update(@PathVariable Long id,
			@RequestBody Office office) throws EntityNotFoundException,
			EntityAlreadyExistsException {
		Office toReturn = officeService.update(id, office);
		return new ResponseEntity<Office>(toReturn, HttpStatus.OK);
	}

	// @PreAuthorize("#username == principal.username")
	@ApiOperation( 
		    value = "Delete office", 
		    notes = "nothing"
		)
	@ApiResponses( {
	    @ApiResponse( code = 404, message = "Office not exists." ) 
	} )
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id)
			throws EntityNotFoundException {
		officeService.delete(id);
		return new ResponseEntity<String>("office deleted.", HttpStatus.NO_CONTENT);
	}

	@ApiOperation( 
		    value = "Get all offices", 
		    notes = "nothing", 
		    response = Office.class,
		    responseContainer = "List"
		)
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Office>> getAll() {
		List<Office> offices = officeService.getAll();
		return new ResponseEntity<List<Office>>(offices, HttpStatus.OK);
	}
}
