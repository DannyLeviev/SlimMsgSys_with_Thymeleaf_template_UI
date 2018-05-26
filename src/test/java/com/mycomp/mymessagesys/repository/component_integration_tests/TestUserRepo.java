package com.mycomp.mymessagesys.repository.component_integration_tests;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.repository.UserDAO;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestUserRepo {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserDAO userRepo;

	private UserDTO user_1;
	private UserDTO user_2;
	private UserDTO user_3;

	public TestUserRepo() {
		user_1 = UserDTO.builder().id(111L).name("user_1").age(11).build();
		user_2 = UserDTO.builder().id(222L).name("user_2").age(22).build();
		user_3 = UserDTO.builder().id(333L).name("user_3").age(33).build();
	}

	@After
	public void entityManagerCleanup() {
		this.entityManager.clear();
	}

	@Test
	public void testGetAllUsers() {
		entityManager.persist(user_1);
		Iterable<UserDTO> users = userRepo.findAll();

		int count = 0;
		for (UserDTO repoUser : users) {
			assertEquals(new Long(111L), repoUser.getId());
			assertEquals("user_1", repoUser.getName());
			assertEquals(11, repoUser.getAge());
			count++;
		}
		assertEquals(1, count);
	}

	@Test
	public void testFindById() {
		entityManager.persist(user_1);
		entityManager.persist(user_2);
		entityManager.persist(user_3);
		Optional<UserDTO> optionalUser = userRepo.findById(222L);

		UserDTO user = optionalUser.get();
		assertEquals(new Long(222L), user.getId());
		assertEquals("user_2", user.getName());
		assertEquals(22, user.getAge());
	}
}
