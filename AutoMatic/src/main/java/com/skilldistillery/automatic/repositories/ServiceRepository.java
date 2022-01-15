package com.skilldistillery.automatic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.automatic.entities.Services;

public interface ServiceRepository extends JpaRepository<Services, Integer> {

}
