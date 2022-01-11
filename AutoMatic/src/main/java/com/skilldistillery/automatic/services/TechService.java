package com.skilldistillery.automatic.services;

import java.util.List;

import com.skilldistillery.automatic.entities.Technician;

public interface TechService {

	public List<Technician> findAllTechs();

	public Technician findTechById(int id);

	public Technician createTech(Technician tech);

	public Technician updateTech(int id, Technician tech);

	public boolean deleteTech(int id);

}
