package project.services;

import project.models.RealStateAgentModel;

import java.util.List;

public interface RealStateAgentService {

    public RealStateAgentModel create(RealStateAgentModel realStateAgent);

    public RealStateAgentModel read(Integer id);

    public RealStateAgentModel update(Integer id, RealStateAgentModel realStateAgent);

    public void delete(Integer id);

    public List<RealStateAgentModel> getAll();
}
