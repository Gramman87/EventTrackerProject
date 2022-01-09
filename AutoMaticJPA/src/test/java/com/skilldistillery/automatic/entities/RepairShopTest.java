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

class RepairShopTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private RepairShop shop;

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
		shop = em.find(RepairShop.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		shop = null;
	}

//	mysql> select * from repair_shop where id = 1;
//	+----+--------+-------------------+---------------+---------------------+
//	| id | name   | location          | phone_number  | created             |
//	+----+--------+-------------------+---------------+---------------------+
//	|  1 | Meevee | 65647 Algoma Lane | (285) 6545998 | 2020-10-11 15:42:29 |
//	+----+--------+-------------------+---------------+---------------------+

	@Test
	@DisplayName("JDBC connection & temporal tests")
	void test1() {
		assertNotNull(shop);
		assertEquals("Meevee", shop.getName());
		assertEquals(11, shop.getCreated().getDayOfMonth());
		assertEquals(2020, shop.getCreated().getYear());
	}

}
