package project.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.CategoryModel;
import project.models.PropertyModel;
import project.repositories.CategoryRepository;
import project.repositories.PropertyRepository;
import project.services.PropertyService;

import java.util.List;

@Service
public class PropertyServiceImplementation implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PropertyModel create(PropertyModel property) {
        PropertyModel toReturn = propertyRepository.save(property);
        return toReturn;
    }

    @Override
    public PropertyModel read(Integer id) {
        PropertyModel toReturn = propertyRepository.findById(id);
        return toReturn;
    }

    @Override
    public PropertyModel update(Integer id, PropertyModel property) {
        PropertyModel toReturn = propertyRepository.findById(id);
        if (property.getDescription() != null) toReturn.setDescription(property.getDescription());
        if (property.getRooms() != null) toReturn.setRooms(property.getRooms());
        if (property.getSize() != null) toReturn.setSize(property.getSize());
        if (property.getState() != null) toReturn.setState(property.getState());
        if (property.getOperation() != null) toReturn.setOperation(property.getOperation());
        propertyRepository.save(toReturn);
        return toReturn;
    }

    @Override
    public void delete(Integer id) {
        propertyRepository.delete(id);
    }

    @Override
    public List<PropertyModel> getAll() {
        return propertyRepository.findAll();
    }

    @Override
    public List<CategoryModel> addCategory(Integer propertyId, Integer categoryId) {
        PropertyModel property = propertyRepository.findById(propertyId);
        CategoryModel category = categoryRepository.findById(categoryId);
        property.getCategories().add(category);
        propertyRepository.save(property);
        return property.getCategories();
    }

    @Override
    public List<CategoryModel> getCategories(Integer propertyId) {
        return propertyRepository.findById(propertyId).getCategories();
    }

    @Override
    public List<CategoryModel> deleteCategory(Integer realStateAgentId, Integer categoryId) {
        PropertyModel property = propertyRepository.findById(realStateAgentId);
        CategoryModel category = categoryRepository.findById(categoryId);
        property.getCategories().remove(category);
        propertyRepository.save(property);
        return property.getCategories();
    }
}
