package com.skilldistillery.automatic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.automatic.entities.User;
import com.skilldistillery.automatic.entities.Vehicle;
import com.skilldistillery.automatic.repositories.UserRepository;
import com.skilldistillery.automatic.repositories.VehicleRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private VehicleRepository vehicleRepo;

	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User findUserById(int id) {
		Optional<User> optUser = userRepo.findById(id);
		if (optUser.isPresent()) {
			return optUser.get();
		}
		return null;
	}

	@Override
	public User createUser(User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User updateUser(int id, User user) {
		Optional<User> optUser = userRepo.findById(id);
		User managed = null;
		if (optUser.isPresent()) {
			managed = optUser.get();
			managed.setEmail(user.getEmail());
			managed.setPassword(user.getPassword());
			managed.setFirstName(user.getFirstName());
			managed.setLastName(user.getLastName());
			userRepo.saveAndFlush(managed);
		}
		return user;
	}

	@Override
	public boolean deleteUser(int id) {
		boolean deleted = false;
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public List<Vehicle> findVehiclesByUsersId(Integer id) {
		if (!userRepo.existsById(id)) {
			return null;
		}
		return vehicleRepo.findByUsersId(id);
	}

}
