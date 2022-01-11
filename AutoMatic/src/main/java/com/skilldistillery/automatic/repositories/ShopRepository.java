package com.skilldistillery.automatic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.automatic.entities.RepairShop;

public interface ShopRepository extends JpaRepository<RepairShop, Integer> {

}
