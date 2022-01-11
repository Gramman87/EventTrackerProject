package com.skilldistillery.automatic.services;

import java.util.List;

import com.skilldistillery.automatic.entities.User;

public interface UserService {

	public List<User> findAllUsers();

	public User findUserById(int id);

	public User createUser(User user);

	public User updateUser(int id, User user);

	public boolean deleteUser(int id);

}
