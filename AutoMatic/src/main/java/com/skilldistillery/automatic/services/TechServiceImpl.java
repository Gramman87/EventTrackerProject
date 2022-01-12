package com.skilldistillery.automatic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.automatic.entities.Technician;
import com.skilldistillery.automatic.repositories.TechRepository;

public class TechServiceImpl implements TechService {

	@Autowired
	private TechRepository techRepo;

	@Override
	public List<Technician> findAllTechs() {
		return techRepo.findAll();
	}

	@Override
	public Technician findTechById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Technician createTech(Technician tech) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Technician updateTech(int id, Technician tech) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteTech(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
