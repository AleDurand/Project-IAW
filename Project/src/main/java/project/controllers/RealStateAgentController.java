package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.models.OfficeModel;
import project.models.PropertyModel;
import project.models.RealStateAgentModel;
import project.services.RealStateAgentService;
import project.validators.RealStateAgentModelValidator;

import java.util.List;

@RestController
@RequestMapping(value = "/real-state-agents")
public class RealStateAgentController {

    @Autowired
    private RealStateAgentService realStateAgentService;

    @Autowired
    private RealStateAgentModelValidator realStateAgentValidator;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<RealStateAgentModel> create(@RequestBody RealStateAgentModel realStateAgent) {
        realStateAgentValidator.validateForCreate(realStateAgent);
        RealStateAgentModel toReturn = realStateAgentService.create(realStateAgent);
        return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RealStateAgentModel> read(@PathVariable Integer id) {
        RealStateAgentModel toReturn = realStateAgentService.read(id);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<RealStateAgentModel> update(@PathVariable Integer id, @RequestBody RealStateAgentModel realStateAgent) {
        realStateAgentValidator.validateForUpdate(realStateAgent);
        RealStateAgentModel toReturn = realStateAgentService.update(id, realStateAgent);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        realStateAgentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RealStateAgentModel>> getAll() {
        List<RealStateAgentModel> realStateAgents = realStateAgentService.getAll();
        return new ResponseEntity<>(realStateAgents, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/offices", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> getOffices(@PathVariable Integer id) {
        List<OfficeModel> offices = realStateAgentService.getOffices(id);
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @RequestMapping(value = "/{realStateAgentId}/offices/{officeId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> addOffice(@PathVariable Integer realStateAgentId, @PathVariable Integer officeId) {
        List<OfficeModel> offices = realStateAgentService.addOffice(realStateAgentId, officeId);
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @RequestMapping(value = "/{realStateAgentId}/offices/{officeId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> deleteOffice(@PathVariable Integer realStateAgentId, @PathVariable Integer officeId) {
        List<OfficeModel> offices = realStateAgentService.deleteOffice(realStateAgentId, officeId);
        return new ResponseEntity<>(offices, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}/properties", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> getProperties(@PathVariable Integer id) {
        List<PropertyModel> properties = realStateAgentService.getProperties(id);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @RequestMapping(value = "/{realStateAgentId}/properties/{propertyId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> addProperty(@PathVariable Integer realStateAgentId, @PathVariable Integer propertyId) {
        List<PropertyModel> properties = realStateAgentService.addProperty(realStateAgentId, propertyId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @RequestMapping(value = "/{realStateAgentId}/properties/{propertyId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> deleteProperty(@PathVariable Integer realStateAgentId, @PathVariable Integer propertyId) {
        List<PropertyModel> properties = realStateAgentService.deleteProperty(realStateAgentId, propertyId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}