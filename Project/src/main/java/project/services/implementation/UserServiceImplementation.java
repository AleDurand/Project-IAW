package project.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.exceptions.AssociationAlreadyExistsException;
import project.exceptions.AssociationNotFoundException;
import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.models.RealEstateAgentModel;
import project.models.UserModel;
import project.repositories.RealEstateAgentRepository;
import project.repositories.UserRepository;
import project.services.UserService;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RealEstateAgentRepository realEstateAgentRepository;

    @Override
    public UserModel create(UserModel user) {
        if (userRepository.findByUsername(user.getUsername()) != null)
            throw new EntityAlreadyExistsException("User", "username", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public UserModel read(Integer id) {
        UserModel toReturn = userRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("User", id);
        return toReturn;
    }

    @Override
    public UserModel update(Integer id, UserModel user) {
        UserModel toReturn = userRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("User", id);
        if (userRepository.findByUsername(user.getUsername()) != null)
            throw new EntityAlreadyExistsException("User", "username", user.getUsername());
        if (user.getUsername() != null) toReturn.setPassword(user.getPassword());
        if (user.getPassword() != null) toReturn.setUsername(user.getUsername());
        return userRepository.save(toReturn);
    }

    @Override
    public void delete(Integer id) {
        if (userRepository.findById(id) == null)
            throw new EntityNotFoundException("User", id);
        userRepository.delete(id);
    }

    @Override
    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<RealEstateAgentModel> addRealEstateAgents(Integer userId, Integer realEstateAgentId) {
        UserModel user = userRepository.findById(userId);
        if (user == null)
            throw new EntityNotFoundException("User", userId);
        RealEstateAgentModel realSateAgent = realEstateAgentRepository.findById(realEstateAgentId);
        if (realSateAgent == null)
            throw new EntityNotFoundException("RealEstateAgent", realEstateAgentId);
        for (RealEstateAgentModel r : user.getRealEstateAgents()) {
            if (r.getId().equals(realSateAgent.getId()))
                throw new AssociationAlreadyExistsException("User", "RealEstateAgent");
        }
        user.getRealEstateAgents().add(realSateAgent);
        userRepository.save(user);
        return user.getRealEstateAgents();
    }

    @Override
    public List<RealEstateAgentModel> getRealEstateAgents(Integer userId) {
        UserModel user = userRepository.findById(userId);
        if (user == null)
            throw new EntityNotFoundException("User", userId);
        return user.getRealEstateAgents();
    }

    @Override
    public List<RealEstateAgentModel> deleteRealEstateAgent(Integer userId, Integer realEstateAgentId) {
        UserModel user = userRepository.findById(userId);
        if (user == null)
            throw new EntityNotFoundException("User", userId);
        RealEstateAgentModel realSateAgent = realEstateAgentRepository.findById(realEstateAgentId);
        if (realSateAgent == null)
            throw new EntityNotFoundException("RealEstateAgent", realEstateAgentId);
        for (RealEstateAgentModel r : user.getRealEstateAgents()) {
            if (r.getId().equals(realSateAgent.getId())) {
                user.getRealEstateAgents().remove(realSateAgent);
                userRepository.save(user);
                return user.getRealEstateAgents();
            }
        }
        throw new AssociationNotFoundException("User", "RealEstateAgent");
    }
}


