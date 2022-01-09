package com.skilldistillery.automatic.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "repair_shop")
public class RepairShop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String location;

	@Column(name = "phone_number")
	private String phoneNumber;

	private LocalDateTime created;

	@JsonIgnore
	@OneToMany(mappedBy = "repairShop")
	private List<Technician> technicians;

	@JsonIgnore
	@ManyToMany(mappedBy = "repairShops")
	private List<Service> services;

	public RepairShop() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public List<Technician> getTechnicians() {
		List<Technician> list = technicians;
		return list;
	}

	public void setTechnicians(List<Technician> technicians) {
		this.technicians = technicians;
	}

	public List<Service> getServices() {
		List<Service> list = services;
		return list;
	}

	public void setServices(List<Service> services) {
		this.services = services;
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
		RepairShop other = (RepairShop) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "RepairShop [id=" + id + ", name=" + name + ", location=" + location + ", phoneNumber=" + phoneNumber
				+ ", created=" + created + "]";
	}

	public void addTech(Technician tech) {
		if (technicians == null) {
			technicians = new ArrayList<>();
		}
		if (!technicians.contains(tech)) {
			technicians.add(tech);
			if (tech.getRepairShop() != null) {
				tech.getRepairShop().getTechnicians().remove(tech);
			}
			tech.setRepairShop(this);
		}
	}

	public void removeTech(Technician tech) {
		tech.setRepairShop(null);
		if (technicians != null) {
			technicians.remove(tech);
		}
	}

}
