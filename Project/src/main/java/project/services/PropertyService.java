package project.services;


import project.models.CategoryModel;
import project.models.PropertyModel;

import java.util.List;

public interface PropertyService {

    public PropertyModel create(PropertyModel property);

    public PropertyModel read(Integer id);

    public PropertyModel update(Integer id, PropertyModel property);

    public void delete(Integer id);

    public List<PropertyModel> getAll();

    public List<CategoryModel> addCategory(Integer propertyId, Integer categoryId);

    public List<CategoryModel> getCategories(Integer propertyId);

    public List<CategoryModel> deleteCategory(Integer propertyId, Integer categoryId);

}
