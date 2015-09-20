package project.services;


import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.exceptions.InvalidIdException;
import project.models.CategoryModel;

import java.util.List;

public interface CategoryService {

    CategoryModel create(CategoryModel category) throws EntityAlreadyExistsException;

    CategoryModel read(Integer id) throws EntityNotFoundException, InvalidIdException;

    CategoryModel update(Integer id, CategoryModel category) throws InvalidIdException, EntityAlreadyExistsException;

    void delete(Integer id) throws EntityNotFoundException, InvalidIdException;

    List<CategoryModel> getAll();

}
