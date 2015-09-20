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
import project.services.CategoryService;
import project.validators.CategoryModelValidator;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@Api(value = "/categories", description = "Manage property categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryModelValidator categoryValidator;

    @ApiOperation(
            value = "Creates a new category",
            notes = "",
            response = CategoryModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Category created", response = CategoryModel.class),
            @ApiResponse(code = 400, message = "Constrains fails", response = DefaultMessage.class)
    })
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CategoryModel> create(@RequestBody CategoryModel category) {
        categoryValidator.validateForCreate(category);
        CategoryModel toReturn = categoryService.create(category);
        return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Returns a category by id",
            notes = "",
            response = CategoryModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Category has been found", response = CategoryModel.class),
            @ApiResponse(code = 400, message = "Invalid Id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Category has not been found", response = CategoryModel.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CategoryModel> read(@PathVariable Integer id) {
        CategoryModel toReturn = categoryService.read(id);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Updates a category by id",
            notes = "",
            response = CategoryModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Category updated", response = CategoryModel.class),
            @ApiResponse(code = 400, message = "Constrains fails or invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Category has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CategoryModel> update(@PathVariable Integer id, @RequestBody CategoryModel category) {
        categoryValidator.validateForUpdate(category);
        CategoryModel toReturn = categoryService.update(id, category);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Deletes a category by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "Category deleted"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "Category has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Gets all categories",
            notes = "",
            response = CategoryModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Categories returned")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CategoryModel>> getAll() {
        List<CategoryModel> categories = categoryService.getAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
