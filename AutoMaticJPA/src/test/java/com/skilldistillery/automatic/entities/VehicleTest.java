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

//	mysql> select * from vehicle where id = 1;
//	+----+-------------------+------+-----------+------+------------+---------------------+-----------------+
//	| id | vin               | make | model     | year | color      | created             | vehicle_type_id |
//	+----+-------------------+------+-----------+------+------------+---------------------+-----------------+
//	|  1 | 2C3CCAJTXCH262146 | Ford | Excursion | 2004 | Aquamarine | 2018-11-19 09:45:44 |               1 |
//	+----+-------------------+------+-----------+------+------------+---------------------+-----------------+

	@Test
	@DisplayName("JDBC connection & temporal tests")
	void test1() {
		assertNotNull(vehicle);
		assertEquals("Ford", vehicle.getMake());
		assertEquals(2018, vehicle.getCreated().getYear());
		assertEquals(11, vehicle.getCreated().getMonthValue());

	}

//	mysql> SELECT COUNT(*) FROM service s JOIN vehicle_service vs ON s.id = vs.service_id JOIN vehicle v ON v.id = vs.vehicle_id WHERE v.id = 1;
//	+----------+
//	| COUNT(*) |
//	+----------+
//	|       10 |
//	+----------+

//	mysql> SELECT COUNT(*) FROM user u JOIN user_vehicle uv ON u.id = uv.user_id JOIN vehicle v ON v.id = uv.vehicle_id WHERE v.id = 1;
//	+----------+
//	| COUNT(*) |
//	+----------+
//	|        3 |
//	+----------+

	@Test
	@DisplayName("entity mapping tests")
	void test2() {
		assertNotNull(vehicle.getServices());
		assertEquals(10, vehicle.getServices().size());
		assertNotNull(vehicle.getUsers());
		assertEquals(3, vehicle.getUsers().size());
	}

}
