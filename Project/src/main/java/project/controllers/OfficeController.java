package project.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.exceptions.messages.DefaultMessage;
import project.models.OfficeModel;
import project.services.OfficeService;
import project.validators.OfficeModelValidator;
import project.validators.Validator;

import java.util.List;

@RestController
@RequestMapping(value = "/offices")
@Api(value = "/offices", description = "Manage offices")
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @Autowired
    private OfficeModelValidator officeValidator;

    @ApiOperation(
            value = "Creates a new office",
            notes = "",
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Office created", response = OfficeModel.class),
            @ApiResponse(code = 400, message = "Constrains fails", response = DefaultMessage.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<OfficeModel> create(@RequestBody OfficeModel office) {
        officeValidator.validateForCreate(office);
        OfficeModel toReturn = officeService.create(office);
        return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Returns an office by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Office has been found", response = OfficeModel.class),
            @ApiResponse(code = 400, message = "Invalid Id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Office has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OfficeModel> read(@PathVariable String id) {
        int officeId = Validator.validateId(id);
        OfficeModel toReturn = officeService.read(officeId);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Updates an office by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Office updated", response = OfficeModel.class),
            @ApiResponse(code = 400, message = "Constrains fails or invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Office has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<OfficeModel> update(@PathVariable String id, @RequestBody OfficeModel office) {
        int officeId = Validator.validateId(id);
        officeValidator.validateForUpdate(office);
        OfficeModel toReturn = officeService.update(officeId, office);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Deletes an office by id",
            notes = "",
            code = 204
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "Office deleted"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Office has not been found", response = DefaultMessage.class)
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        int officeId = Validator.validateId(id);
        officeService.delete(officeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Gets all offices",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Offices returned", response = OfficeModel.class, responseContainer = "List")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> getAll() {
        List<OfficeModel> offices = officeService.getAll();
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }
}
