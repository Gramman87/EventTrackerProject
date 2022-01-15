package com.skilldistillery.automatic.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.automatic.entities.Vehicle;
import com.skilldistillery.automatic.services.VehicleService;

@RestController
@RequestMapping("api")
public class VehicleController {

	@Autowired
	private VehicleService vehicleSvc;

	@GetMapping("vehicles")
	public List<Vehicle> vehicles() {
		return vehicleSvc.findAllVehicles();
	}

	@GetMapping("vehicles/{id}")
	public Vehicle showVehicle(@PathVariable Integer id, HttpServletResponse res) {
		Vehicle vehicle = vehicleSvc.findById(id);
		if (vehicle == null) {
			res.setStatus(404);
		}
		return vehicle;
	}

	@PostMapping("vehicles")
	public Vehicle createVehicle(@RequestBody Vehicle vehicle, HttpServletRequest req, HttpServletResponse res) {
		try {
			vehicleSvc.createVehicle(vehicle);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(vehicle.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.print("INVALID ENTRY FOR NEW Vehicle");
			res.setStatus(400);
			vehicle = null;
		}
		return vehicle;
	}

	@PutMapping("vehicles/{id}")
	public Vehicle updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle, HttpServletResponse res) {
		try {
			vehicle = vehicleSvc.updateVehicle(id, vehicle);
			if (vehicle == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			vehicle = null;
		}
		return vehicle;
	}

	@DeleteMapping("vehicles/{id}")
	public void deleteVehicle(@PathVariable Integer id, HttpServletResponse res) {
		try {
			if (vehicleSvc.deleteVehicle(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

}
