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

class ServicesTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Services service;

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
		service = em.find(Services.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		service = null;
	}

//	mysql> select * from service where id = 1;
//	+----+--------------+---------------------+----------+---------+---------------+
//	| id | type         | created             | odometer | cost    | technician_id |
//	+----+--------------+---------------------+----------+---------+---------------+
//	|  1 | Stucco Mason | 2018-05-24 12:39:13 |   287846 | 2506.29 |            22 |
//	+----+--------------+---------------------+----------+---------+---------------+

	@Test
	@DisplayName("JDBC connection & temporal tests")
	void test1() {
		assertNotNull(service);
		assertEquals("Stucco Mason", service.getType());
		assertEquals(05, service.getCreated().getMonthValue());
	}
	
//	mysql> SELECT COUNT(*) FROM service s JOIN service_location sl ON s.id = sl.service_id JOIN repair_shop rs ON rs.id = sl.repair_shop_id WHERE s.id = 1;
//	+----------+
//	| COUNT(*) |
//	+----------+
//	|        1 |
//	+----------+
	
	@Test
	@DisplayName("entity mapping tests")
	void test2() {
		assertNotNull(service.getRepairShops());
	}

}
