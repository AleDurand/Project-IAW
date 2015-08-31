package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.models.OfficeModel;
import project.services.OfficeService;

import java.util.List;

@RestController
@RequestMapping(value = "/offices")
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<OfficeModel> create(@RequestBody OfficeModel office) {
        OfficeModel toReturn = officeService.create(office);
        return new ResponseEntity<OfficeModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OfficeModel> read(@PathVariable Integer id) {
        OfficeModel toReturn = officeService.read(id);
        return new ResponseEntity<OfficeModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<OfficeModel> update(@PathVariable Integer id, @RequestBody OfficeModel office) {
        OfficeModel toReturn = officeService.update(id, office);
        return new ResponseEntity<OfficeModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        officeService.delete(id);
        return new ResponseEntity<String>("OfficeModel deleted.", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OfficeModel>> getAll() {
        List<OfficeModel> offices = officeService.getAll();
        return new ResponseEntity<List<OfficeModel>>(offices, HttpStatus.OK);
    }
}
