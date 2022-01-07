package com.skilldistillery.automatic.services;

import java.util.List;

import com.skilldistillery.automatic.entities.Vehicle;

public interface VehicleService {
	
	List<Vehicle> findAllVehicles();
	Vehicle findById(int id);

}
