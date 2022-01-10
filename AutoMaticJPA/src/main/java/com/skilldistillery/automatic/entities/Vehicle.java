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
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String vin;

	private String make;

	private String model;

	private Integer year;

	private String color;

	@CreationTimestamp
	private LocalDateTime created;

	@ManyToOne
	@JoinColumn(name = "vehicle_type_id")
	private VehicleType vehicleType;

	@JsonIgnore
	@ManyToMany(mappedBy = "vehicles")
	private List<Service> services;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_vehicle", joinColumns = @JoinColumn(name = "vehicle_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;

	public Vehicle() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public List<Service> getServices() {
		List<Service> list = services;
		return list;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<User> getUsers() {
		List<User> list = users;
		return list;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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
		Vehicle other = (Vehicle) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", vin=" + vin + ", make=" + make + ", model=" + model + ", year=" + year
				+ ", color=" + color + ", created=" + created + ", vehicleTypeId=" + vehicleType + "]";
	}

	public void addService(Service service) {
		if (services == null) {
			services = new ArrayList<>();
		}
		if (!services.contains(service)) {
			services.add(service);
			service.addVehicle(this);
		}
	}

	public void removeService(Service service) {
		if (services != null && services.contains(service)) {
			services.remove(service);
			service.removeVehicle(this);
		}
	}

	public void addUser(User user) {
		if (users == null) {
			users = new ArrayList<>();
		}
		if (!users.contains(user)) {
			users.add(user);
			user.addVehicle(this);
		}
	}

	public void removeUser(User user) {
		if (users != null && users.contains(user)) {
			users.remove(user);
			user.removeVehicle(this);
		}
	}

}
