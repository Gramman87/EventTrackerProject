package com.skilldistillery.automatic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.automatic.entities.Service;
import com.skilldistillery.automatic.repositories.ServiceRepository;

public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceRepository serviceRepo;

	@Override
	public List<Service> findAllService() {
		return serviceRepo.findAll();
	}

	@Override
	public Service findByServiceId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service createService(Service service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service updateService(int id, Service service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteService(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
