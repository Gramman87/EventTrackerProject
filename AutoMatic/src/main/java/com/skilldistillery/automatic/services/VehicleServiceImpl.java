package com.skilldistillery.automatic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.automatic.entities.Vehicle;
import com.skilldistillery.automatic.entities.VehicleType;
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
		if (vehicleOpt.isPresent()) {
			return vehicleOpt.get();
		}
		return null;
	}

	@Override
	public Vehicle createVehicle(Vehicle vehicle) {
		if (vehicle.getVehicleType() == null) {
			VehicleType type = new VehicleType();
			type.setId(1);
			vehicle.setVehicleType(type);
			vehicleRepo.saveAndFlush(vehicle);
		}
		return vehicle;
	}

	@Override
	public Vehicle updateVehicle(int vehicleId, Vehicle vehicle) {
		Optional<Vehicle> optVehicle = vehicleRepo.findById(vehicleId);
		Vehicle managed = null;
		if (optVehicle.isPresent()) {
			managed = optVehicle.get();
			managed.setVin(vehicle.getVin());
			managed.setMake(vehicle.getMake());
			managed.setModel(vehicle.getModel());
			managed.setYear(vehicle.getYear());
			managed.setColor(vehicle.getColor());
			managed.setCreated(vehicle.getCreated());
			if (vehicle.getVehicleType() != null) {
				managed.setVehicleType(vehicle.getVehicleType());
			}
			vehicleRepo.saveAndFlush(managed);
		}
		return vehicle;
	}

	@Override
	public boolean deleteVehicle(int vehicleId) {
		boolean deleted = false;
		if (vehicleRepo.existsById(vehicleId)) {
			vehicleRepo.deleteById(vehicleId);
			deleted = true;
		}
		return deleted;
	}

}
