package com.skilldistillery.automatic.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.automatic.entities.User;
import com.skilldistillery.automatic.services.UserService;

@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	private UserService userSvc;
	
	@GetMapping("users")
	public List<User> users() {
		return userSvc.findAllUsers();
	}
	
	@GetMapping(user/{id})
	public User showUser(@PathVariable Integer id, HttpServletResponse res) {
		User user = userSvc.findUserById(id);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}

}
