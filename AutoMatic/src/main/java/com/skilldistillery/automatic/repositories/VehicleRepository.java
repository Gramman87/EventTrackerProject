package com.skilldistillery.automatic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.automatic.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
