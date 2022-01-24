package com.skilldistillery.automatic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skilldistillery.automatic.entities.User;

public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserService userSvc;

	@Override
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userSvc.createUser(user);
		return null;
	}

}
