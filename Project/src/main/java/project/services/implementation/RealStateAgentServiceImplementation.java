package project.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.models.OfficeModel;
import project.models.PropertyModel;
import project.models.RealStateAgentModel;
import project.repositories.OfficeRepository;
import project.repositories.PropertyRepository;
import project.repositories.RealStateAgentRepository;
import project.services.RealStateAgentService;

@Service
public class RealStateAgentServiceImplementation implements RealStateAgentService {
    @Autowired
    private RealStateAgentRepository realStateAgentRepository;
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    
    @Override
    public RealStateAgentModel create(RealStateAgentModel realStateAgent) {
        RealStateAgentModel toReturn = realStateAgentRepository.save(realStateAgent);
        return toReturn;
    }

    @Override
    public RealStateAgentModel read(Integer id) {
        RealStateAgentModel toReturn = realStateAgentRepository.findById(id);
        return toReturn;
    }

    @Override
    public RealStateAgentModel update(Integer id, RealStateAgentModel realStateAgent) {
        RealStateAgentModel toReturn = realStateAgentRepository.findById(id);
        toReturn.setName(realStateAgent.getName());
        toReturn.setDescription(realStateAgent.getDescription());
        toReturn.setEmail(realStateAgent.getEmail());
        toReturn.setWeb(realStateAgent.getWeb());
        realStateAgentRepository.save(toReturn);
        return toReturn;
    }

    @Override
    public void delete(Integer id) {
        realStateAgentRepository.delete(id);
    }

    @Override
    public List<RealStateAgentModel> getAll() {
        return realStateAgentRepository.findAll();
    }

	@Override
	public List<OfficeModel> addOffice(Integer realStateAgentId, Integer officeId) {
		RealStateAgentModel realStateAgent = realStateAgentRepository.findById(realStateAgentId);
        OfficeModel office = officeRepository.findById(realStateAgentId);
        realStateAgent.getOffices().add(office);
        realStateAgentRepository.save(realStateAgent);
        return realStateAgent.getOffices();
	}

	@Override
	public List<OfficeModel> getOffices(Integer realStateAgentId) {
		return realStateAgentRepository.findById(realStateAgentId).getOffices();
	}

	@Override
	public List<OfficeModel> deleteOffice(Integer realStateAgentId, Integer officeId) {
		RealStateAgentModel realStateAgent = realStateAgentRepository.findById(realStateAgentId);
        OfficeModel office = officeRepository.findById(officeId);
        realStateAgent.getOffices().remove(office);
        realStateAgentRepository.save(realStateAgent);
        return realStateAgent.getOffices();
	}

	@Override
	public List<PropertyModel> addProperty(Integer realStateAgentId, Integer propertyId) {
		RealStateAgentModel realStateAgent = realStateAgentRepository.findById(realStateAgentId);
        PropertyModel property = propertyRepository.findById(propertyId);
        realStateAgent.getProperties().add(property);
        realStateAgentRepository.save(realStateAgent);
        return realStateAgent.getProperties();
	}

	@Override
	public List<PropertyModel> getProperties(Integer realStateAgentId) {
		return realStateAgentRepository.findById(realStateAgentId).getProperties();
	}

	@Override
	public List<PropertyModel> deleteProperty(Integer realStateAgentId,	Integer propertyId) {
		RealStateAgentModel realStateAgent = realStateAgentRepository.findById(realStateAgentId);
        PropertyModel property = propertyRepository.findById(propertyId);
        realStateAgent.getOffices().remove(property);
        realStateAgentRepository.save(realStateAgent);
        return realStateAgent.getProperties();
	}
}
