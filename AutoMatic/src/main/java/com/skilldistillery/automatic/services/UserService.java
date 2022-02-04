package com.skilldistillery.automatic.services;

import java.util.List;

import com.skilldistillery.automatic.entities.User;
import com.skilldistillery.automatic.entities.Vehicle;

public interface UserService {

	public List<User> findAllUsers();

	public User findByUsername(String name);

	public User findUserById(int id);

	public User createUser(User user);

	public User updateUser(String name, int id, User user);

	public boolean deleteUser(int id);

	public List<Vehicle> findVehiclesByUsersId(Integer id);

}
