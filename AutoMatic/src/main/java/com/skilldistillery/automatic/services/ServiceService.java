package com.skilldistillery.automatic.services;

import java.util.List;

import com.skilldistillery.automatic.entities.Service;

public interface ServiceService {

	public List<Service> findAllService();

	public Service findByServiceId(int id);

	public Service createService(Service service);

	public Service updateService(int id, Service service);

	public boolean deleteService(int id);

}
