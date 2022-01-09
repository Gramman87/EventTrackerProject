package com.skilldistillery.automatic.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;

	private LocalDateTime created;

	private int odometer;

	private double cost;

	@Column(name = "technician_id")
	private int techId;

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

	public int getTechId() {
		return techId;
	}

	public void setTechId(int techId) {
		this.techId = techId;
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
				+ cost + ", techId=" + techId + "]";
	}

}
