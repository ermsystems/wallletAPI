package com.manager.systems.webapi.wallet.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.manager.systems.webapi.wallet.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
	}

	@Test
	public void testSave() {
		final User user = new User();
		user.setName("User Test");
		user.setEmail("manager@manager.com");
		user.setPassword("123456");

		final User response = this.userRepository.save(user);
		assertNotNull(response);
	}

	@Test
	public void testFindByEmail() {

		final String email = "manager@manager.com";

		final Optional<User> response = this.userRepository.findByEmailEquals(email);
		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), email);
	}
}