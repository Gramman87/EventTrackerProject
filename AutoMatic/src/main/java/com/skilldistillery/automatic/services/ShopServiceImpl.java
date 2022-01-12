package com.skilldistillery.automatic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.automatic.entities.RepairShop;
import com.skilldistillery.automatic.repositories.ShopRepository;

public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopRepository shopRepo;

	@Override
	public List<RepairShop> findAllShops() {
		return shopRepo.findAll();
	}

	@Override
	public RepairShop findShopById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepairShop createShop(RepairShop shop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepairShop updateShop(int id, RepairShop shop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteShop(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
