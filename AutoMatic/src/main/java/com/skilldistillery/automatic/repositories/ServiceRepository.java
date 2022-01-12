package com.skilldistillery.automatic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.automatic.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
