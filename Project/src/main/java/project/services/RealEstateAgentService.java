package project.services;

import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.exceptions.InvalidIdException;
import project.models.OfficeModel;
import project.models.PropertyModel;
import project.models.RealEstateAgentModel;

import java.util.List;

public interface RealEstateAgentService {

    RealEstateAgentModel create(RealEstateAgentModel realEstateAgent) throws EntityAlreadyExistsException;

    RealEstateAgentModel read(Integer id) throws EntityNotFoundException, InvalidIdException;

    RealEstateAgentModel update(Integer id, RealEstateAgentModel realEstateAgent) throws InvalidIdException, EntityAlreadyExistsException;

    void delete(Integer id) throws EntityNotFoundException, InvalidIdException;

    List<RealEstateAgentModel> getAll();

    List<OfficeModel> addOffice(Integer realEstateAgentId, Integer officeId) throws EntityNotFoundException;

    List<OfficeModel> getOffices(Integer realEstateAgentId) throws EntityNotFoundException;

    List<OfficeModel> deleteOffice(Integer realEstateAgentId, Integer officeId) throws EntityNotFoundException;

    List<PropertyModel> addProperty(Integer realEstateAgentId, Integer propertyId) throws EntityNotFoundException;

    List<PropertyModel> getProperties(Integer realEstateAgentId) throws EntityNotFoundException;

    List<PropertyModel> deleteProperty(Integer realEstateAgentId, Integer propertyId) throws EntityNotFoundException;

}
