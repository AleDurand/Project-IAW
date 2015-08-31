package project.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.PropertyModel;
import project.repositories.PropertyRepository;
import project.services.PropertyService;

import java.util.List;

@Service
public class PropertyServiceImplementation implements PropertyService {
    @Autowired
    private PropertyRepository PropertyRepository;

    @Override
    public PropertyModel create(PropertyModel property) {
        PropertyModel toReturn = PropertyRepository.save(property);
        return toReturn;
    }

    @Override
    public PropertyModel read(Integer id) {
        PropertyModel toReturn = PropertyRepository.findById(id);
        return toReturn;
    }

    @Override
    public PropertyModel update(Integer id, PropertyModel property) {
        PropertyModel toReturn = PropertyRepository.findById(id);
        toReturn.setDescription(property.getDescription());
        toReturn.setRooms(property.getRooms());
        toReturn.setSize(property.getSize());
        toReturn.setState(property.getState());
        PropertyRepository.save(toReturn);
        return toReturn;
    }

    @Override
    public void delete(Integer id) {
        PropertyRepository.delete(id);
    }

    @Override
    public List<PropertyModel> getAll() {
        return PropertyRepository.findAll();
    }
}
