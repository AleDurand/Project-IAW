package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.models.CategoryModel;
import project.models.PropertyModel;
import project.services.PropertyService;

import java.util.List;

@RestController
@RequestMapping(value = "/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<PropertyModel> create(@RequestBody PropertyModel property) {
        PropertyModel toReturn = propertyService.create(property);
        return new ResponseEntity<PropertyModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PropertyModel> read(@PathVariable Integer id) {
        PropertyModel toReturn = propertyService.read(id);
        return new ResponseEntity<PropertyModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<PropertyModel> update(@PathVariable Integer id, @RequestBody PropertyModel property) {
        PropertyModel toReturn = propertyService.update(id, property);
        return new ResponseEntity<PropertyModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        propertyService.delete(id);
        return new ResponseEntity<String>("PropertyModel deleted.", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> getAll() {
        List<PropertyModel> properties = propertyService.getAll();
        return new ResponseEntity<List<PropertyModel>>(properties, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/categories", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CategoryModel>> getCategories(@PathVariable Integer id) {
        List<CategoryModel> categories = propertyService.getCategories(id);
        return new ResponseEntity<List<CategoryModel>>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/{propertyId}/categories/{categoryId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<CategoryModel>> addCategory(@PathVariable Integer propertyId, @PathVariable Integer categoryId) {
        List<CategoryModel> categories = propertyService.addCategory(propertyId, categoryId);
        return new ResponseEntity<List<CategoryModel>>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/{propertyId}/categories/{categoryId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<CategoryModel>> deleteCategory(@PathVariable Integer propertyId, @PathVariable Integer categoryId) {
        List<CategoryModel> categories = propertyService.deleteCategory(propertyId, categoryId);
        return new ResponseEntity<List<CategoryModel>>(categories, HttpStatus.OK);
    }
}
