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
import project.models.PropertyModel;
import project.models.RealEstateAgentModel;
import project.services.RealEstateAgentService;
import project.validators.RealEstateAgentModelValidator;
import project.validators.Validator;

import java.util.List;

@RestController
@RequestMapping(value = "/real-estate-agents")
@Api(value = "/real-estate-agents", description = "Manage real estate agents")
public class RealEstateAgentController {

    @Autowired
    private RealEstateAgentService realEstateAgentService;

    @Autowired
    private RealEstateAgentModelValidator realEstateAgentValidator;

    @ApiOperation(
            value = "Creates a new real estate agent",
            notes = "",
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "RealEstateAgent created", response = RealEstateAgentModel.class),
            @ApiResponse(code = 400, message = "Constrains fails", response = DefaultMessage.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<RealEstateAgentModel> create(@RequestBody RealEstateAgentModel realEstateAgent) {
        realEstateAgentValidator.validateForCreate(realEstateAgent);
        RealEstateAgentModel toReturn = realEstateAgentService.create(realEstateAgent);
        return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Returns a real estate agent by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "RealEstateAgent has been found", response = RealEstateAgentModel.class),
            @ApiResponse(code = 400, message = "Invalid Id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "RealEstateAgent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RealEstateAgentModel> read(@PathVariable String id) {
        int realEstateAgentId = Validator.validateId(id);
        RealEstateAgentModel toReturn = realEstateAgentService.read(realEstateAgentId);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Updates a real estate agent by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "RealEstateAgent updated", response = RealEstateAgentModel.class),
            @ApiResponse(code = 400, message = "Constrains fails or invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "RealEstateAgent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<RealEstateAgentModel> update(@PathVariable String id, @RequestBody RealEstateAgentModel realEstateAgent) {
        int realEstateAgentId = Validator.validateId(id);
        realEstateAgentValidator.validateForUpdate(realEstateAgent);
        RealEstateAgentModel toReturn = realEstateAgentService.update(realEstateAgentId, realEstateAgent);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Deletes a real estate agent by id",
            notes = "",
            code = 204
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "RealEstateAgent deleted"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "RealEstateAgent has not been found", response = DefaultMessage.class)
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        int realEstateAgentId = Validator.validateId(id);
        realEstateAgentService.delete(realEstateAgentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Gets all real estate agents",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "RealEstateAgents returned", response = RealEstateAgentModel.class, responseContainer = "List")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RealEstateAgentModel>> getAll() {
        List<RealEstateAgentModel> realEstateAgents = realEstateAgentService.getAll();
        return new ResponseEntity<>(realEstateAgents, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Gets all offices belongs to a particular real estate agent",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the offices associated with the real estate agent", response = OfficeModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}/offices", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> getOffices(@PathVariable String id) {
        int realEstateAgentId = Validator.validateId(id);
        List<OfficeModel> offices = realEstateAgentService.getOffices(realEstateAgentId);
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Associates an office with a particular real estate agent",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Associates an office to the real estate agent and returns the offices associated", response = OfficeModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id or association already exists", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent or office has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realEstateAgentId}/offices/{officeId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> addOffice(@PathVariable String realEstateAgentId, @PathVariable String officeId) {
        int realEstateAgentIdValidated = Validator.validateId(realEstateAgentId);
        int officeIdValidated = Validator.validateId(officeId);
        List<OfficeModel> offices = realEstateAgentService.addOffice(realEstateAgentIdValidated, officeIdValidated);
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Removes the association between a real estate agent and an office",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deletes an association and returns the offices associated with the real estate agent", response = OfficeModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent or office or association has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realEstateAgentId}/offices/{officeId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> deleteOffice(@PathVariable String realEstateAgentId, @PathVariable String officeId) {
        int realEstateAgentIdValidated = Validator.validateId(realEstateAgentId);
        int officeIdValidated = Validator.validateId(officeId);
        List<OfficeModel> offices = realEstateAgentService.deleteOffice(realEstateAgentIdValidated, officeIdValidated);
        return new ResponseEntity<>(offices, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Gets all properties belongs to a particular real estate agent",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the the properties associated with the real estate agent", response = PropertyModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}/properties", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> getProperties(@PathVariable String id) {
        int realEstateAgentId = Validator.validateId(id);
        List<PropertyModel> properties = realEstateAgentService.getProperties(realEstateAgentId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Associates a property with a particular real estate agent",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Associates a property to the real estate agent and returns the properties associated", response = PropertyModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id or association already exists", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent or property has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realEstateAgentId}/properties/{propertyId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> addProperty(@PathVariable String realEstateAgentId, @PathVariable String propertyId) {
        int realEstateAgentIdValidated = Validator.validateId(realEstateAgentId);
        int propertyIdValidated = Validator.validateId(propertyId);
        List<PropertyModel> properties = realEstateAgentService.addProperty(realEstateAgentIdValidated, propertyIdValidated);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Removes the association between a real estate agent and a property",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deletes an association and returns the properties associated with the real estate agent", response = PropertyModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent or property or association has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realEstateAgentId}/properties/{propertyId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> deleteProperty(@PathVariable String realEstateAgentId, @PathVariable String propertyId) {
        int realEstateAgentIdValidated = Validator.validateId(realEstateAgentId);
        int propertyIdValidated = Validator.validateId(propertyId);
        List<PropertyModel> properties = realEstateAgentService.deleteProperty(realEstateAgentIdValidated, propertyIdValidated);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}