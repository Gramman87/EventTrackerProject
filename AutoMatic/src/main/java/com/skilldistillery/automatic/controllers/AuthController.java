package com.skilldistillery.automatic.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.automatic.entities.User;
import com.skilldistillery.automatic.services.AuthService;

@RestController
@CrossOrigin({ "*", "http://localhost:4200" })
public class AuthController {

	@Autowired
	private AuthService authSvc;

	@PostMapping("/register")
	public User register(@RequestBody User user, HttpServletResponse res) {
		if (user == null) {
			res.setStatus(400);
		}
		user = authSvc.register(user);
		return user;
	}

	@GetMapping("/authenticate")
	public Principal authenticate(Principal principal) {
		return principal;
	}

}
