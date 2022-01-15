package com.skilldistillery.automatic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.automatic.entities.Services;
import com.skilldistillery.automatic.repositories.ServiceRepository;

@Service
public class ServicesServiceImpl implements ServicesService {

	@Autowired
	private ServiceRepository serviceRepo;

	@Override
	public List<Services> findAllServices() {
		return serviceRepo.findAll();
	}

	@Override
	public Services findByServicesId(int id) {
		Optional<Services> optService = serviceRepo.findById(id);
		if (optService.isPresent()) {
			return optService.get();
		}
		return null;
	}

	@Override
	public Services createServices(Services service) {
		return serviceRepo.saveAndFlush(service);
	}

	@Override
	public Services updateServices(int id, Services service) {
		Optional<Services> optService = serviceRepo.findById(id);
		Services managed = null;
		if (optService.isPresent()) {
			managed = optService.get();
			managed.setType(service.getType());
			managed.setOdometer(service.getOdometer());
			managed.setCost(service.getCost());
			serviceRepo.saveAndFlush(managed);
		}
		return service;
	}

	@Override
	public boolean deleteServices(int id) {
		boolean deleted = false;
		if (serviceRepo.existsById(id)) {
			serviceRepo.deleteById(id);
			deleted = true;
		}
		return deleted;
	}

}
