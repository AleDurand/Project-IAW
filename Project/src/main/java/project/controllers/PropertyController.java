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
import project.models.PropertyModel;
import project.services.PropertyService;
import project.validators.PropertyModelValidator;
import project.validators.Validator;

import java.util.List;

@RestController
@RequestMapping(value = "/properties")
@Api(value = "/properties", description = "Manage properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyModelValidator propertyValidator;

    @ApiOperation(
            value = "Creates a new property",
            notes = "",
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Property created", response = PropertyModel.class),
            @ApiResponse(code = 400, message = "Constrains fails", response = DefaultMessage.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<PropertyModel> create(@RequestBody PropertyModel property) {
        propertyValidator.validateForCreate(property);
        PropertyModel toReturn = propertyService.create(property);
        return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Returns a property by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Property has been found", response = PropertyModel.class),
            @ApiResponse(code = 400, message = "Invalid Id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Property has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PropertyModel> read(@PathVariable String id) {
        int propertyId = Validator.validateId(id);
        PropertyModel toReturn = propertyService.read(propertyId);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Updates a property by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Property updated", response = PropertyModel.class),
            @ApiResponse(code = 400, message = "Constrains fails or invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Property has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<PropertyModel> update(@PathVariable String id, @RequestBody PropertyModel property) {
        int propertyId = Validator.validateId(id);
        propertyValidator.validateForUpdate(property);
        PropertyModel toReturn = propertyService.update(propertyId, property);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Deletes a property by id",
            notes = "",
            code = 204
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "Property deleted"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Property has not been found", response = DefaultMessage.class)
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        int propertyId = Validator.validateId(id);
        propertyService.delete(propertyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Gets all properties",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Properties returned", response = PropertyModel.class, responseContainer = "List")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PropertyModel>> getAll() {
        List<PropertyModel> properties = propertyService.getAll();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Gets all categories belongs to a particular property",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the categories associated with the property", response = CategoryModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Real estate agent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}/categories", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CategoryModel>> getCategories(@PathVariable String id) {
        int propertyId = Validator.validateId(id);
        List<CategoryModel> categories = propertyService.getCategories(propertyId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Associates a category with a particular property",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Associates a category to the property and returns the categories associated", response = CategoryModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id or association already exists", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Category or property has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{propertyId}/categories/{categoryId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<CategoryModel>> addCategory(@PathVariable String propertyId, @PathVariable String categoryId) {
        int propertyIdValidated = Validator.validateId(propertyId);
        int categoryIdValidated = Validator.validateId(categoryId);
        List<CategoryModel> categories = propertyService.addCategory(propertyIdValidated, categoryIdValidated);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Removes the association between a category and a property",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deletes an association and returns the categories associated with the property", response = CategoryModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Category or property or association has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{propertyId}/categories/{categoryId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<CategoryModel>> deleteCategory(@PathVariable String propertyId, @PathVariable String categoryId) {
        int propertyIdValidated = Validator.validateId(propertyId);
        int categoryIdValidated = Validator.validateId(categoryId);
        List<CategoryModel> categories = propertyService.deleteCategory(propertyIdValidated, categoryIdValidated);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
