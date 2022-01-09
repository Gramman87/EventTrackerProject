package com.skilldistillery.automatic.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle_type")
public class VehicleType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;

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

}
