package com.skilldistillery.automatic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.automatic.entities.Technician;

public interface TechRepository extends JpaRepository<Technician, Integer> {

}
