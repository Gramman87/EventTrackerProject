package com.skilldistillery.automatic.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;

	private LocalDateTime created;

	private int odometer;

	private double cost;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "service_location", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "repair_shop_id"))
	private List<RepairShop> repairShops;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "technician_services", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "technician_id"))
	private List<Technician> technicians;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "vehicle_service", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
	private List<Vehicle> vehicles;

	public Service() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public int getOdometer() {
		return odometer;
	}

	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<RepairShop> getRepairShops() {
		return repairShops;
	}

	public void setRepairShops(List<RepairShop> repairShops) {
		this.repairShops = repairShops;
	}

	public List<Technician> getTechnicians() {
		return technicians;
	}

	public void setTechnicians(List<Technician> tech) {
		this.technicians = tech;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", type=" + type + ", created=" + created + ", odometer=" + odometer + ", cost="
				+ cost + "]";
	}

	public void addTechnician(Technician tech) {
		if (technicians == null) {
			technicians = new ArrayList<>();
		}
		if (!technicians.contains(tech)) {
			technicians.add(tech);
			tech.addService(this);
		}
	}

	public void removeTechnician(Technician tech) {
		if (technicians != null && technicians.contains(tech)) {
			technicians.remove(tech);
			tech.removeService(this);
		}
	}
	
	public void addVehicle(Vehicle vehicle) {
		if (vehicles == null) {
			vehicles = new ArrayList<>();
		}
		if (!vehicles.contains(vehicle)) {
			vehicles.add(vehicle);
			vehicle.addService(this);
		}
	}
	
	public void removeVehicle(Vehicle vehicle) {
		if (vehicles != null && vehicles.contains(vehicle)) {
			vehicles.remove(vehicle);
			vehicle.removeService(this);
		}
	}

}
