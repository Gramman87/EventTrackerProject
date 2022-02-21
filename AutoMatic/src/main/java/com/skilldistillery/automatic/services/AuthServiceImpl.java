package com.skilldistillery.automatic.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.automatic.entities.User;
import com.skilldistillery.automatic.repositories.UserRepository;

@Transactional
@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User findUserByName(String username) {
		return userRepo.findByUsername(username);
	}

}
