package com.skilldistillery.automatic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.automatic.entities.Vehicle;
import com.skilldistillery.automatic.services.VehicleService;

@RestController
@RequestMapping("api")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleSvc;
	
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}
	
	@GetMapping("index")
	public List<Vehicle> index() {
		return vehicleSvc.findAllVehicles();
	}

}
