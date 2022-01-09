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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Technician {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private LocalDateTime created;

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private RepairShop repairShop;

	@JsonIgnore
	@ManyToMany(mappedBy = "technicians")
	private List<Service> services;

	public Technician() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public RepairShop getRepairShop() {
		return repairShop;
	}

	public void setRepairShop(RepairShop repairShop) {
		this.repairShop = repairShop;
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
		Technician other = (Technician) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Technician [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", created=" + created
				+ "]";
	}

	public void addService(Service service) {
		if (services == null) {
			services = new ArrayList<>();
		}
		if (!services.contains(service)) {
			services.add(service);
			service.addTechnician(this);
		}
	}

	public void removeService(Service service) {
		if (services != null && services.contains(service)) {
			services.remove(service);
			service.removeTechnician(this);
		}
	}

}
