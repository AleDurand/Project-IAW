package project.services;


import project.exceptions.EntityNotFoundException;
import project.exceptions.InvalidIdException;
import project.models.CategoryModel;
import project.models.PropertyModel;

import java.util.List;

public interface PropertyService {

    PropertyModel create(PropertyModel property);

    PropertyModel read(Integer id) throws EntityNotFoundException, InvalidIdException;

    PropertyModel update(Integer id, PropertyModel property) throws InvalidIdException;

    void delete(Integer id) throws EntityNotFoundException, InvalidIdException;

    List<PropertyModel> getAll();

    List<CategoryModel> addCategory(Integer propertyId, Integer categoryId) throws EntityNotFoundException;

    List<CategoryModel> getCategories(Integer propertyId) throws EntityNotFoundException;

    List<CategoryModel> deleteCategory(Integer propertyId, Integer categoryId) throws EntityNotFoundException;

}
