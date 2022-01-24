package com.skilldistillery.automatic.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.automatic.entities.User;

@Transactional
@Service
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
