package project.services;

import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.exceptions.InvalidIdException;
import project.models.RealStateAgentModel;
import project.models.UserModel;

import java.util.List;

public interface UserService {

    UserModel create(UserModel user) throws EntityAlreadyExistsException;

    UserModel read(Integer id) throws EntityNotFoundException, InvalidIdException;

    UserModel update(Integer id, UserModel user) throws InvalidIdException, EntityAlreadyExistsException;

    void delete(Integer id) throws EntityNotFoundException, InvalidIdException;

    List<UserModel> getAll();

    List<RealStateAgentModel> addRealStateAgents(Integer userId, Integer realStateAgentId) throws EntityNotFoundException;

    List<RealStateAgentModel> getRealStateAgents(Integer userId) throws EntityNotFoundException;

    List<RealStateAgentModel> deleteRealSateAgent(Integer userId, Integer realStateAgentId) throws EntityNotFoundException;


}
