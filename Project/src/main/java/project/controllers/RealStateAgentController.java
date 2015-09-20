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
import project.models.CategoryModel;
import project.models.OfficeModel;
import project.models.PropertyModel;
import project.models.RealStateAgentModel;
import project.services.RealStateAgentService;
import project.validators.RealStateAgentModelValidator;

import java.util.List;

@RestController
@RequestMapping(value = "/real-state-agents")
@Api(value = "/real-state-agents", description = "Manage real state agents")
public class RealStateAgentController {

    @Autowired
    private RealStateAgentService realStateAgentService;

    @Autowired
    private RealStateAgentModelValidator realStateAgentValidator;

    @ApiOperation(
            value = "Creates a new real state agent",
            notes = "",
            response = RealStateAgentModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "RealStateAgent created", response = RealStateAgentModel.class),
            @ApiResponse(code = 400, message = "Constrains fails", response = DefaultMessage.class)
    })
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<RealStateAgentModel> create(@RequestBody RealStateAgentModel realStateAgent) {
        realStateAgentValidator.validateForCreate(realStateAgent);
        RealStateAgentModel toReturn = realStateAgentService.create(realStateAgent);
        return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Returns a real state agent by id",
            notes = "",
            response = RealStateAgentModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "RealStateAgent has been found", response = RealStateAgentModel.class),
            @ApiResponse(code = 400, message = "Invalid Id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "RealStateAgent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RealStateAgentModel> read(@PathVariable Integer id) {
        RealStateAgentModel toReturn = realStateAgentService.read(id);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Updates a real state agent by id",
            notes = "",
            response = RealStateAgentModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "RealStateAgent updated", response = RealStateAgentModel.class),
            @ApiResponse(code = 400, message = "Constrains fails or invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "RealStateAgent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<RealStateAgentModel> update(@PathVariable Integer id, @RequestBody RealStateAgentModel realStateAgent) {
        realStateAgentValidator.validateForUpdate(realStateAgent);
        RealStateAgentModel toReturn = realStateAgentService.update(id, realStateAgent);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Deletes a real state agent by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "RealStateAgent deleted"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "RealStateAgent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        realStateAgentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Gets all real state agents",
            notes = "",
            response = RealStateAgentModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "RealStateAgents returned")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RealStateAgentModel>> getAll() {
        List<RealStateAgentModel> realStateAgents = realStateAgentService.getAll();
        return new ResponseEntity<>(realStateAgents, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Gets all offices belongs to a particular real state agent",
            notes = "",
            response = OfficeModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Offices associated with the real state agent"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real state agent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}/offices", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> getOffices(@PathVariable Integer id) {
        List<OfficeModel> offices = realStateAgentService.getOffices(id);
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Associate a office with a particular real state agent",
            notes = "",
            response = OfficeModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Offices associated with the real state agent"),
            @ApiResponse(code = 400, message = "Invalid id or association already exists", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real state agent or office has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realStateAgentId}/offices/{officeId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> addOffice(@PathVariable Integer realStateAgentId, @PathVariable Integer officeId) {
        List<OfficeModel> offices = realStateAgentService.addOffice(realStateAgentId, officeId);
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Removes the association between a real state agent and an office",
            notes = "",
            response = OfficeModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Offices associated with the real state agent"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real state agent or office or association has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realStateAgentId}/offices/{officeId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> deleteOffice(@PathVariable Integer realStateAgentId, @PathVariable Integer officeId) {
        List<OfficeModel> offices = realStateAgentService.deleteOffice(realStateAgentId, officeId);
        return new ResponseEntity<>(offices, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Gets all properties belongs to a particular real state agent",
            notes = "",
            response = PropertyModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Properties associated with the real state agent"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real state agent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}/properties", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> getProperties(@PathVariable Integer id) {
        List<PropertyModel> properties = realStateAgentService.getProperties(id);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Associate a property with a particular real state agent",
            notes = "",
            response = PropertyModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Properties associated with the real state agent"),
            @ApiResponse(code = 400, message = "Invalid id or association already exists", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real state agent or property has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realStateAgentId}/properties/{propertyId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> addProperty(@PathVariable Integer realStateAgentId, @PathVariable Integer propertyId) {
        List<PropertyModel> properties = realStateAgentService.addProperty(realStateAgentId, propertyId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Removes the association between a real state agent and a property",
            notes = "",
            response = PropertyModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Properties associated with the real state agent"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real state agent or property or association has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{realStateAgentId}/properties/{propertyId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> deleteProperty(@PathVariable Integer realStateAgentId, @PathVariable Integer propertyId) {
        List<PropertyModel> properties = realStateAgentService.deleteProperty(realStateAgentId, propertyId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}