package project.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.exceptions.AssociationAlreadyExistsException;
import project.exceptions.AssociationNotFoundException;
import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.models.OfficeModel;
import project.models.PropertyModel;
import project.models.RealEstateAgentModel;
import project.repositories.OfficeRepository;
import project.repositories.PropertyRepository;
import project.repositories.RealEstateAgentRepository;
import project.services.RealEstateAgentService;

import java.util.List;

@Service
public class RealEstateAgentServiceImplementation implements RealEstateAgentService {
    @Autowired
    private RealEstateAgentRepository realEstateAgentRepository;
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public RealEstateAgentModel create(RealEstateAgentModel realEstateAgent) {
        if (realEstateAgentRepository.findByName(realEstateAgent.getName()) != null)
            throw new EntityAlreadyExistsException("RealEstateAgent", "name", realEstateAgent.getName());
        return realEstateAgentRepository.save(realEstateAgent);
    }

    @Override
    public RealEstateAgentModel read(Integer id) {
        RealEstateAgentModel toReturn = realEstateAgentRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("RealEstateAgent", id);
        return toReturn;
    }

    @Override
    public RealEstateAgentModel update(Integer id, RealEstateAgentModel realEstateAgent) {
        RealEstateAgentModel toReturn = realEstateAgentRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("RealEstateAgent", id);
        if (realEstateAgentRepository.findByName(realEstateAgent.getName()) != null)
            throw new EntityAlreadyExistsException("RealEstateAgent", "name", realEstateAgent.getName());
        if (realEstateAgent.getName() != null) toReturn.setName(realEstateAgent.getName());
        if (realEstateAgent.getDescription() != null) toReturn.setDescription(realEstateAgent.getDescription());
        if (realEstateAgent.getEmail() != null) toReturn.setEmail(realEstateAgent.getEmail());
        if (realEstateAgent.getWeb() != null) toReturn.setWeb(realEstateAgent.getWeb());
        realEstateAgentRepository.save(toReturn);
        return toReturn;
    }

    @Override
    public void delete(Integer id) {
        RealEstateAgentModel toReturn = realEstateAgentRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("RealEstateAgent", id);
        realEstateAgentRepository.delete(id);
    }

    @Override
    public List<RealEstateAgentModel> getAll() {
        return realEstateAgentRepository.findAll();
    }

    @Override
    public List<OfficeModel> addOffice(Integer realEstateAgentId, Integer officeId) {
        RealEstateAgentModel realEstateAgent = realEstateAgentRepository.findById(realEstateAgentId);
        if (realEstateAgent == null)
            throw new EntityNotFoundException("RealEstateAgent", realEstateAgentId);
        OfficeModel office = officeRepository.findById(officeId);
        if (office == null)
            throw new EntityNotFoundException("Office", officeId);
        for (OfficeModel o : realEstateAgent.getOffices()) {
            if (o.getId().equals(office.getId()))
                throw new AssociationAlreadyExistsException("RealEstateAgent", "Office");
        }
        realEstateAgent.getOffices().add(office);
        realEstateAgentRepository.save(realEstateAgent);
        return realEstateAgent.getOffices();
    }

    @Override
    public List<OfficeModel> getOffices(Integer realEstateAgentId) {
        RealEstateAgentModel realEstateAgent = realEstateAgentRepository.findById(realEstateAgentId);
        if (realEstateAgent == null)
            throw new EntityNotFoundException("RealEstateAgent", realEstateAgentId);
        return realEstateAgent.getOffices();
    }

    @Override
    public List<OfficeModel> deleteOffice(Integer realEstateAgentId, Integer officeId) {
        RealEstateAgentModel realEstateAgent = realEstateAgentRepository.findById(realEstateAgentId);
        if (realEstateAgent == null)
            throw new EntityNotFoundException("RealEstateAgent", realEstateAgentId);
        OfficeModel office = officeRepository.findById(officeId);
        if (office == null)
            throw new EntityNotFoundException("Office", officeId);
        for (OfficeModel o : realEstateAgent.getOffices()) {
            if (o.getId().equals(office.getId())) {
                realEstateAgent.getOffices().remove(office);
                realEstateAgentRepository.save(realEstateAgent);
                return realEstateAgent.getOffices();
            }
        }
        throw new AssociationNotFoundException("RealEstateAgent", "Office");
    }

    @Override
    public List<PropertyModel> addProperty(Integer realEstateAgentId, Integer propertyId) {
        RealEstateAgentModel realEstateAgent = realEstateAgentRepository.findById(realEstateAgentId);
        if (realEstateAgent == null)
            throw new EntityNotFoundException("RealEstateAgent", realEstateAgentId);
        PropertyModel property = propertyRepository.findById(propertyId);
        if (property == null)
            throw new EntityNotFoundException("Property", propertyId);
        for (PropertyModel p : realEstateAgent.getProperties()) {
            if (p.getId().equals(property.getId()))
                throw new AssociationAlreadyExistsException("RealEstateAgent", "Property");
        }
        realEstateAgent.getProperties().add(property);
        realEstateAgentRepository.save(realEstateAgent);
        return realEstateAgent.getProperties();
    }

    @Override
    public List<PropertyModel> getProperties(Integer realEstateAgentId) {
        RealEstateAgentModel realEstateAgent = realEstateAgentRepository.findById(realEstateAgentId);
        if (realEstateAgent == null)
            throw new EntityNotFoundException("RealEstateAgent", realEstateAgentId);
        return realEstateAgent.getProperties();
    }

    @Override
    public List<PropertyModel> deleteProperty(Integer realEstateAgentId, Integer propertyId) {
        RealEstateAgentModel realEstateAgent = realEstateAgentRepository.findById(realEstateAgentId);
        if (realEstateAgent == null)
            throw new EntityNotFoundException("RealEstateAgent", realEstateAgentId);
        PropertyModel property = propertyRepository.findById(propertyId);
        if (property == null)
            throw new EntityNotFoundException("Property", propertyId);
        for (PropertyModel p : realEstateAgent.getProperties()) {
            if (p.getId().equals(property.getId())) {
                realEstateAgent.getProperties().remove(property);
                realEstateAgentRepository.save(realEstateAgent);
                return realEstateAgent.getProperties();
            }
        }
        throw new AssociationNotFoundException("RealEstateAgent", "Property");

    }
}
