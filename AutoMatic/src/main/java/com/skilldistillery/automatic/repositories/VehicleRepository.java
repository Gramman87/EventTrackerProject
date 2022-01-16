package com.skilldistillery.automatic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.automatic.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	
	List<Vehicle> findByUsersId(Integer id);

}
