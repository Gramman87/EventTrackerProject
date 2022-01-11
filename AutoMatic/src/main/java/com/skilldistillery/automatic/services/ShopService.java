package com.skilldistillery.automatic.services;

import java.util.List;

import com.skilldistillery.automatic.entities.RepairShop;

public interface ShopService {

	public List<RepairShop> findAllShops();

	public RepairShop findShopById(int id);

	public RepairShop createShop(RepairShop shop);

	public RepairShop updateShop(int id, RepairShop shop);

	public boolean deleteShop(int id);

}
