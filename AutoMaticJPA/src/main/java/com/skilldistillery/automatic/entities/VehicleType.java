package com.skilldistillery.automatic.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vehicle_type")
public class VehicleType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;

	@JsonIgnore
	@OneToMany(mappedBy = "vehicleType")
	private List<Vehicle> vehicles;

	public VehicleType() {
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

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleType other = (VehicleType) obj;
		return id == other.id && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "VehicleType [id=" + id + ", type=" + type + "]";
	}

	public void addVehicle(Vehicle vehicle) {
		if (vehicles == null) {
			vehicles = new ArrayList<>();
		}
		if (!vehicles.contains(vehicle)) {
			vehicles.add(vehicle);
			if (vehicle.getVehicleType() != null) {
				vehicle.getVehicleType().getVehicles().remove(vehicle);
			}
			vehicle.setVehicleType(this);
		}
	}

	public void removeVehicle(Vehicle vehicle) {
		vehicle.setVehicleType(null);
		if (vehicles != null) {
			vehicles.remove(vehicle);
		}
	}

}
