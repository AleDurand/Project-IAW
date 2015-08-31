package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.models.CategoryModel;
import project.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CategoryModel> create(@RequestBody CategoryModel category) {
        CategoryModel toReturn = categoryService.create(category);
        return new ResponseEntity<CategoryModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CategoryModel> read(@PathVariable Integer id) {
        CategoryModel toReturn = categoryService.read(id);
        return new ResponseEntity<CategoryModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CategoryModel> update(@PathVariable Integer id, @RequestBody CategoryModel category) {
        CategoryModel toReturn = categoryService.update(id, category);
        return new ResponseEntity<CategoryModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return new ResponseEntity<String>("CategoryModel deleted.", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CategoryModel>> getAll() {
        List<CategoryModel> categories = categoryService.getAll();
        return new ResponseEntity<List<CategoryModel>>(categories, HttpStatus.OK);
    }
}
