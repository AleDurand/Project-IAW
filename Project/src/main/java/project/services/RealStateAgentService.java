package project.services;

import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.exceptions.InvalidIdException;
import project.models.OfficeModel;
import project.models.PropertyModel;
import project.models.RealStateAgentModel;

import java.util.List;

public interface RealStateAgentService {

    RealStateAgentModel create(RealStateAgentModel realStateAgent) throws EntityAlreadyExistsException;

    RealStateAgentModel read(Integer id) throws EntityNotFoundException, InvalidIdException;

    RealStateAgentModel update(Integer id, RealStateAgentModel realStateAgent) throws InvalidIdException, EntityAlreadyExistsException;

    void delete(Integer id) throws EntityNotFoundException, InvalidIdException;

    List<RealStateAgentModel> getAll();

    List<OfficeModel> addOffice(Integer realStateAgentId, Integer officeId) throws EntityNotFoundException;

    List<OfficeModel> getOffices(Integer realStateAgentId) throws EntityNotFoundException;

    List<OfficeModel> deleteOffice(Integer realStateAgentId, Integer officeId) throws EntityNotFoundException;

    List<PropertyModel> addProperty(Integer realStateAgentId, Integer propertyId) throws EntityNotFoundException;

    List<PropertyModel> getProperties(Integer realStateAgentId) throws EntityNotFoundException;

    List<PropertyModel> deleteProperty(Integer realStateAgentId, Integer propertyId) throws EntityNotFoundException;

}
