package project.services;

import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.RealStateAgentModel;
import project.models.UserModel;

import java.util.List;

public interface UserService {

    UserModel create(UserModel user) throws InvalidEntityConstraintsException, EntityAlreadyExistsException;

    UserModel read(Integer id);

    UserModel update(Integer id, UserModel user) throws InvalidEntityConstraintsException, EntityAlreadyExistsException;

    void delete(Integer id);

    List<UserModel> getAll();

    List<RealStateAgentModel> addRealStateAgents(Integer userId, Integer realStateAgentId);

    List<RealStateAgentModel> getRealStateAgents(Integer userId);

    List<RealStateAgentModel> deleteRealSateAgent(Integer userId, Integer realStateAgentId);


}
