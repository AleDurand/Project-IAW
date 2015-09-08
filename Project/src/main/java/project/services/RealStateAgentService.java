package project.services;

import java.util.List;

import project.models.OfficeModel;
import project.models.PropertyModel;
import project.models.RealStateAgentModel;

public interface RealStateAgentService {

    public RealStateAgentModel create(RealStateAgentModel realStateAgent);

    public RealStateAgentModel read(Integer id);

    public RealStateAgentModel update(Integer id, RealStateAgentModel realStateAgent);

    public void delete(Integer id);

    public List<RealStateAgentModel> getAll();

    public List<OfficeModel> addOffice(Integer realStateAgentId, Integer officeId);
    
    public List<OfficeModel> getOffices(Integer realStateAgentId);
    
    public List<OfficeModel> deleteOffice(Integer realStateAgentId, Integer officeId);
    
    public List<PropertyModel> addProperty(Integer realStateAgentId, Integer propertyId);
    
    public List<PropertyModel> getProperties(Integer realStateAgentId);
    
    public List<PropertyModel> deleteProperty(Integer realStateAgentId, Integer propertyId);
    
}
