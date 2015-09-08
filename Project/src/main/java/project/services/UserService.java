package project.services;

import project.models.RealStateAgentModel;
import project.models.UserModel;

import java.util.List;

public interface UserService {

    public UserModel create(UserModel user);

    public UserModel read(Integer id);

    public UserModel update(Integer id, UserModel user);

    public void delete(Integer id);

    public List<UserModel> getAll();

    public List<RealStateAgentModel> addRealStateAgents(Integer userId, Integer realStateAgentId);

    public List<RealStateAgentModel> getRealStateAgents(Integer userId);

    public List<RealStateAgentModel> deleteRealSateAgent(Integer userId, Integer realStateAgentId);


}
