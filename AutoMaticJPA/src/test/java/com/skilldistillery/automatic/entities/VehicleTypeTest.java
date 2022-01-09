package com.skilldistillery.automatic.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VehicleTypeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private VehicleType vehicleType;

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
		vehicleType = em.find(VehicleType.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		vehicleType = null;
	}

//	mysql> select * from vehicle_type where id = 1;
//	+----+-------------+
//	| id | type        |
//	+----+-------------+
//	|  1 | Terry-Kozey |
//	+----+-------------+

	@Test
	@DisplayName("JDBC connection tests")
	void test1() {
		assertNotNull(vehicleType);
		assertEquals(1, vehicleType.getId());
		assertEquals("Terry-Kozey", vehicleType.getType());
	}

}
