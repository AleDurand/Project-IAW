package project.services;

import java.util.List;

import project.models.UserModel;

public interface UserService {
	
	public UserModel create(UserModel user);
	
	public UserModel read(Integer id);
	
	public UserModel update(Integer id, UserModel user);
	
	public void delete(Integer id);
	
	public List<UserModel> getAll();
}
