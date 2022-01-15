package com.skilldistillery.automatic.services;

import java.util.List;

import com.skilldistillery.automatic.entities.Services;

public interface ServicesService {

	public List<Services> findAllServices();

	public Services findByServicesId(int id);

	public Services createServices(Services service);

	public Services updateServices(int id, Services service);

	public boolean deleteServices(int id);

}
