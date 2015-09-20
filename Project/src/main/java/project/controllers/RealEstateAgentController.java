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
            response = RealEstateAgentModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "RealEstateAgent created", response = RealEstateAgentModel.class),
            @ApiResponse(code = 400, message = "Constrains fails", response = DefaultMessage.class)
    })
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<RealEstateAgentModel> create(@RequestBody RealEstateAgentModel realEstateAgent) {
        realEstateAgentValidator.validateForCreate(realEstateAgent);
        RealEstateAgentModel toReturn = realEstateAgentService.create(realEstateAgent);
        return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Returns a real estate agent by id",
            notes = "",
            response = RealEstateAgentModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "RealEstateAgent has been found", response = RealEstateAgentModel.class),
            @ApiResponse(code = 400, message = "Invalid Id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "RealEstateAgent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RealEstateAgentModel> read(@PathVariable Integer id) {
        RealEstateAgentModel toReturn = realEstateAgentService.read(id);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Updates a real estate agent by id",
            notes = "",
            response = RealEstateAgentModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "RealEstateAgent updated", response = RealEstateAgentModel.class),
            @ApiResponse(code = 400, message = "Constrains fails or invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "RealEstateAgent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<RealEstateAgentModel> update(@PathVariable Integer id, @RequestBody RealEstateAgentModel realEstateAgent) {
        realEstateAgentValidator.validateForUpdate(realEstateAgent);
        RealEstateAgentModel toReturn = realEstateAgentService.update(id, realEstateAgent);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Deletes a real estate agent by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "RealEstateAgent deleted"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "RealEstateAgent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        realEstateAgentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Gets all real estate agents",
            notes = "",
            response = RealEstateAgentModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "RealEstateAgents returned")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RealEstateAgentModel>> getAll() {
        List<RealEstateAgentModel> realEstateAgents = realEstateAgentService.getAll();
        return new ResponseEntity<>(realEstateAgents, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Gets all offices belongs to a particular real estate agent",
            notes = "",
            response = OfficeModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Offices associated with the real estate agent"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}/offices", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> getOffices(@PathVariable Integer id) {
        List<OfficeModel> offices = realEstateAgentService.getOffices(id);
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Associate a office with a particular real estate agent",
            notes = "",
            response = OfficeModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Offices associated with the real estate agent"),
            @ApiResponse(code = 400, message = "Invalid id or association already exists", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent or office has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realEstateAgentId}/offices/{officeId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> addOffice(@PathVariable Integer realEstateAgentId, @PathVariable Integer officeId) {
        List<OfficeModel> offices = realEstateAgentService.addOffice(realEstateAgentId, officeId);
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Removes the association between a real estate agent and an office",
            notes = "",
            response = OfficeModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Offices associated with the real estate agent"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent or office or association has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realEstateAgentId}/offices/{officeId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> deleteOffice(@PathVariable Integer realEstateAgentId, @PathVariable Integer officeId) {
        List<OfficeModel> offices = realEstateAgentService.deleteOffice(realEstateAgentId, officeId);
        return new ResponseEntity<>(offices, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Gets all properties belongs to a particular real estate agent",
            notes = "",
            response = PropertyModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Properties associated with the real estate agent"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}/properties", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> getProperties(@PathVariable Integer id) {
        List<PropertyModel> properties = realEstateAgentService.getProperties(id);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Associate a property with a particular real estate agent",
            notes = "",
            response = PropertyModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Properties associated with the real estate agent"),
            @ApiResponse(code = 400, message = "Invalid id or association already exists", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent or property has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realEstateAgentId}/properties/{propertyId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> addProperty(@PathVariable Integer realEstateAgentId, @PathVariable Integer propertyId) {
        List<PropertyModel> properties = realEstateAgentService.addProperty(realEstateAgentId, propertyId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Removes the association between a real estate agent and a property",
            notes = "",
            response = PropertyModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Properties associated with the real estate agent"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent or property or association has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realEstateAgentId}/properties/{propertyId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> deleteProperty(@PathVariable Integer realEstateAgentId, @PathVariable Integer propertyId) {
        List<PropertyModel> properties = realEstateAgentService.deleteProperty(realEstateAgentId, propertyId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}