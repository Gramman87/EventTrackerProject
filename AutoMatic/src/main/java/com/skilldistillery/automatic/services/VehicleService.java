package com.skilldistillery.automatic.services;

import java.util.List;

import com.skilldistillery.automatic.entities.Vehicle;

public interface VehicleService {

	public List<Vehicle> findAllVehicles();

	public Vehicle findById(int id);

	public Vehicle createVehicle(Vehicle vehicle);

	public Vehicle updateVehicle(int vehicleId, Vehicle vehicle);

	public boolean deleteVehicle(int vehicleId);

}
