package project.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.RealStateAgentModel;
import project.models.UserModel;
import project.repositories.RealStateAgentRepository;
import project.repositories.UserRepository;
import project.services.UserService;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RealStateAgentRepository realStateAgentRepository;

    @Override
    public UserModel create(UserModel user) {
        UserModel toReturn = userRepository.save(user);
        return toReturn;
    }

    @Override
    public UserModel read(Integer id) {
        UserModel toReturn = userRepository.findById(id);
        return toReturn;
    }

    @Override
    public UserModel update(Integer id, UserModel user) {
        UserModel toReturn = userRepository.findById(id);
        toReturn.setPassword(user.getPassword());
        toReturn.setUsername(user.getUsername());
        userRepository.save(toReturn);
        return toReturn;
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<RealStateAgentModel> addRealStateAgents(Integer userId, Integer realStateAgentId) {
        UserModel user = userRepository.findById(userId);
        RealStateAgentModel realSateAgent = realStateAgentRepository.findById(realStateAgentId);
        user.getRealStateAgents().add(realSateAgent);
        userRepository.save(user);
        return user.getRealStateAgents();
    }

    @Override
    public List<RealStateAgentModel> getRealStateAgents(Integer userId) {
        return userRepository.findById(userId).getRealStateAgents();
    }

    @Override
    public List<RealStateAgentModel> deleteRealSateAgent(Integer userId, Integer realStateAgentId) {
        UserModel user = userRepository.findById(userId);
        RealStateAgentModel realSateAgent = realStateAgentRepository.findById(realStateAgentId);
        user.getRealStateAgents().remove(realSateAgent);
        userRepository.save(user);
        return user.getRealStateAgents();
    }
}
