package project.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.exceptions.AssociationAlreadyExistsException;
import project.exceptions.AssociationNotFoundException;
import project.exceptions.EntityNotFoundException;
import project.models.CategoryModel;
import project.models.PropertyModel;
import project.repositories.AddressRepository;
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
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public PropertyModel create(PropertyModel property) {
        property.setAddress(addressRepository.save(property.getAddress()));
        return propertyRepository.save(property);
    }

    @Override
    public PropertyModel read(Integer id) {
        PropertyModel toReturn = propertyRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("Property", id);
        return toReturn;
    }

    @Override
    public PropertyModel update(Integer id, PropertyModel property) {
        PropertyModel toReturn = propertyRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("Property", id);
        if (property.getDescription() != null) toReturn.setDescription(property.getDescription());
        if (property.getRooms() != null) toReturn.setRooms(property.getRooms());
        if (property.getSize() != null) toReturn.setSize(property.getSize());
        if (property.getState() != null) toReturn.setState(property.getState());
        if (property.getOperation() != null) toReturn.setOperation(property.getOperation());
        if (property.getAddress() != null) {
            if (property.getAddress().getCity() != null) toReturn.getAddress().setCity(property.getAddress().getCity());
            if (property.getAddress().getStreet() != null)
                toReturn.getAddress().setStreet(property.getAddress().getStreet());
            if (property.getAddress().getSuite() != null)
                toReturn.getAddress().setSuite(property.getAddress().getSuite());
            if (property.getAddress().getZipCode() != null)
                toReturn.getAddress().setZipCode(property.getAddress().getZipCode());
            if (property.getAddress().getGeoLocation() != null)
                toReturn.getAddress().setGeoLocation(property.getAddress().getGeoLocation());
        }
        return propertyRepository.save(toReturn);
    }

    @Override
    public void delete(Integer id) {
        PropertyModel toReturn = propertyRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("Property", id);
        propertyRepository.delete(id);
    }

    @Override
    public List<PropertyModel> getAll() {
        return propertyRepository.findAll();
    }

    @Override
    public List<CategoryModel> addCategory(Integer propertyId, Integer categoryId) {
        PropertyModel property = propertyRepository.findById(propertyId);
        if (property == null)
            throw new EntityNotFoundException("Property", propertyId);
        CategoryModel category = categoryRepository.findById(categoryId);
        if (category == null)
            throw new EntityNotFoundException("Category", categoryId);
        for (CategoryModel c : property.getCategories()) {
            if (c.getId().equals(category.getId()))
                throw new AssociationAlreadyExistsException("Property", "Category");
        }
        property.getCategories().add(category);
        propertyRepository.save(property);
        return property.getCategories();
    }

    @Override
    public List<CategoryModel> getCategories(Integer propertyId) {
        PropertyModel property = propertyRepository.findById(propertyId);
        if (property == null)
            throw new EntityNotFoundException("Property", propertyId);
        return property.getCategories();
    }

    @Override
    public List<CategoryModel> deleteCategory(Integer propertyId, Integer categoryId) {
        PropertyModel property = propertyRepository.findById(propertyId);
        if (property == null)
            throw new EntityNotFoundException("Property", propertyId);
        CategoryModel category = categoryRepository.findById(categoryId);
        if (category == null)
            throw new EntityNotFoundException("Category", categoryId);
        for (CategoryModel c : property.getCategories()) {
            if (c.getId().equals(category.getId())) {
                property.getCategories().remove(category);
                propertyRepository.save(property);
                return property.getCategories();
            }
        }
        throw new AssociationNotFoundException("Property", "Category");

    }
}
