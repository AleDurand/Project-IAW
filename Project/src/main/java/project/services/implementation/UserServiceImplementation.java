package project.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.models.UserModel;
import project.repositories.UserRepository;
import project.services.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

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
}
