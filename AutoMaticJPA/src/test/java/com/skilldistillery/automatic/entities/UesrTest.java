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

class UesrTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}
	
//	mysql> select * from user where id = 1;
//	+----+----------------------+-------------+------------+-----------+---------------------+
//	| id | email                | password    | first_name | last_name | created             |
//	+----+----------------------+-------------+------------+-----------+---------------------+
//	|  1 | amccallam0@google.ca | NdokpCvD44i | Anni       | McCallam  | 2012-10-30 10:08:36 |
//	+----+----------------------+-------------+------------+-----------+---------------------+

	@Test
	@DisplayName("JDBC connection & temporal tests")
	void test1() {
		assertNotNull(user);
		assertEquals("Anni", user.getFirstName());
		assertEquals(10, user.getCreated().getMonthValue());
		assertEquals(30, user.getCreated().getDayOfMonth());
	}

}
