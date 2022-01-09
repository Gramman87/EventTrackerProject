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

class TechnicianTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Technician tech;

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
		tech = em.find(Technician.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		tech = null;
	}

//	mysql> select * from technician where id = 1;
//	+----+------------+------------+---------------------+---------+
//	| id | first_name | last_name  | created             | shop_id |
//	+----+------------+------------+---------------------+---------+
//	|  1 | Gertie     | Pietrowicz | 2018-03-23 11:58:38 |       9 |
//	+----+------------+------------+---------------------+---------+

	@Test
	@DisplayName("JDBC connection & temporal tests")
	void test1() {
		assertNotNull(tech);
		assertEquals("Gertie", tech.getFirstName());
		assertEquals(2018, tech.getCreated().getYear());
	}

}
