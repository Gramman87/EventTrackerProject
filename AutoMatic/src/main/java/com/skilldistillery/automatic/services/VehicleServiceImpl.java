package com.skilldistillery.automatic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.automatic.entities.Vehicle;
import com.skilldistillery.automatic.repositories.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepo;

	@Override
	public List<Vehicle> findAllVehicles() {
		return vehicleRepo.findAll();
	}

	@Override
	public Vehicle findById(int id) {
		Optional<Vehicle> vehicleOpt = vehicleRepo.findById(id);
		if(vehicleOpt.isPresent()) {
			return vehicleOpt.get();
		}
		return null;
	}

}
