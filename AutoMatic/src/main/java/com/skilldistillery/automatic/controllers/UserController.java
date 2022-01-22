package com.skilldistillery.automatic.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.automatic.entities.User;
import com.skilldistillery.automatic.entities.Vehicle;
import com.skilldistillery.automatic.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4202"})
public class UserController {

	@Autowired
	private UserService userSvc;

	@GetMapping("users")
	public List<User> users() {
		return userSvc.findAllUsers();
	}

	@GetMapping("users/{id}")
	public User showUser(@PathVariable Integer id, HttpServletResponse res) {
		User user = userSvc.findUserById(id);
		if (user == null) {
			res.setStatus(404);
		} 
		return user;
	}

	@PostMapping("users")
	public User createUser(@RequestBody User user, HttpServletRequest req, HttpServletResponse res) {
		try {
			userSvc.createUser(user);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(user.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("INVALID ENTRY FOR NEW User");
			res.setStatus(400);
			user = null;
		}
		return user;
	}

	@PutMapping("users/{id}")
	public User updateUser(@PathVariable Integer id, @RequestBody User user, HttpServletResponse res) {
		try {
			user = userSvc.updateUser(id, user);
			if (user == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			user = null;
		}
		return user;
	}

	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable Integer id, HttpServletResponse res) {
		try {
			if (userSvc.deleteUser(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	@GetMapping("users/{id}/vehicles")
	public List<Vehicle> usersVehicles(@PathVariable Integer id, HttpServletResponse res) {
		List<Vehicle> vehicles = userSvc.findVehiclesByUsersId(id);
		if (vehicles == null) {
			res.setStatus(404);
		}
		return vehicles;
	}

}
