package com.skilldistillery.automatic.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.automatic.entities.Services;
import com.skilldistillery.automatic.services.ServicesService;

@RestController
@RequestMapping("api")
public class ServiceController {

	@Autowired
	private ServicesService servicesSvc;

	@GetMapping("services")
	public List<Services> services() {
		return servicesSvc.findAllServices();
	}

	@GetMapping("services/{id}")
	public Services showServices(@PathVariable Integer id, HttpServletResponse res) {
		Services service = servicesSvc.findByServicesId(id);
		if (service == null) {
			res.setStatus(404);
		}
		return service;
	}

	@PostMapping("services")
	public Services createService(@RequestBody Services service, HttpServletRequest req, HttpServletResponse res) {
		try {
			servicesSvc.createServices(service);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(service.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("INVALID ENTRY FOR NEW Services");
			res.setStatus(400);
			service = null;
		}
		return service;
	}

	@PutMapping("services/{id}")
	public Services updateService(@PathVariable Integer id, @RequestBody Services service, HttpServletResponse res) {
		try {
			service = servicesSvc.updateServices(id, service);
			if (service == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			service = null;
		}
		return service;
	}

	@DeleteMapping("services/{id}")
	public void deleteService(@PathVariable Integer id, HttpServletResponse res) {
		try {
			if (servicesSvc.deleteServices(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

}
