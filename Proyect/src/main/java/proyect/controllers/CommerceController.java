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
import proyect.model.Commerce;
import proyect.services.CommerceService;

@RestController
@RequestMapping(value = "/commerces")
@Api( value = "/commerces", description = "Manage commerces" )
// @PreAuthorize("#username == principal.username")
public class CommerceController {
	@Autowired
	private CommerceService commerceService;
	
	
	@ApiOperation( 
		    value = "Create commerce", 
		    notes = "nothing", 
		    response = Commerce.class
		)
	@ApiResponses( {
	    @ApiResponse( code = 400, message = "Commerce already exists." )    
	} )
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Commerce> create(@RequestBody Commerce commerce)
			throws EntityAlreadyExistsException {
		Commerce toReturn = commerceService.create(commerce);
		return new ResponseEntity<Commerce>(toReturn, HttpStatus.OK);
	}

	// @PreAuthorize("#username == principal.username")
	@ApiOperation( 
		    value = "Get commerce by id", 
		    notes = "nothing", 
		    response = Commerce.class
		)
	@ApiResponses( {
	    @ApiResponse( code = 404, message = "Commerce not exists." )    
	} )
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Commerce> read(@PathVariable Long id)
			throws EntityNotFoundException {
		Commerce toReturn = commerceService.read(id);
		return new ResponseEntity<Commerce>(toReturn, HttpStatus.OK);
	}

	// @PreAuthorize("#username == principal.username")
	@ApiOperation( 
		    value = "Update commerce", 
		    notes = "nothing", 
		    response = Commerce.class
		)
	@ApiResponses( {
	    @ApiResponse( code = 404, message = "Commerce not exists." ),
	    @ApiResponse( code = 400, message = "Commerce already exists." )    
	} )
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Commerce> update(@PathVariable Long id,
			@RequestBody Commerce commerce) throws EntityNotFoundException,
			EntityAlreadyExistsException {
		Commerce toReturn = commerceService.update(id, commerce);
		return new ResponseEntity<Commerce>(toReturn, HttpStatus.OK);
	}

	// @PreAuthorize("#username == principal.username")
	@ApiOperation( 
		    value = "Delete commerce", 
		    notes = "nothing"
		)
	@ApiResponses( {
	    @ApiResponse( code = 404, message = "Commerce not exists." ) 
	} )
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id)
			throws EntityNotFoundException {
		commerceService.delete(id);
		return new ResponseEntity<String>("commerce deleted.", HttpStatus.NO_CONTENT);
	}

	@ApiOperation( 
		    value = "Get all commerces", 
		    notes = "nothing", 
		    response = Commerce.class,
		    responseContainer = "List"
		)
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Commerce>> getAll() {
		List<Commerce> commerces = commerceService.getAll();
		return new ResponseEntity<List<Commerce>>(commerces, HttpStatus.OK);
	}
}
