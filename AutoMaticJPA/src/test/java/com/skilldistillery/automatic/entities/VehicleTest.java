package com.skilldistillery.automatic.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VehicleTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Vehicle vehicle;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("AutoMaticJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		vehicle = em.find(Vehicle.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		vehicle = null;
	}

	@Test
	@DisplayName("entity mapping")
	void test1() {
		assertNotNull(vehicle);
		assertEquals("G's Bucket", vehicle.getName());
	}

}
