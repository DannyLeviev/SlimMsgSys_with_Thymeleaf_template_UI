package com.mycomp.mymessagesys.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.repository.UserDAO;
import com.mycomp.mymessagesys.service.exceptions.InvalidUserIdException;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

	/*
	 * During component scanning, we might find components or configurations
	 * (created only for specific tests) accidentally get picked up everywhere. To
	 * help prevent that, Spring Boot provides @TestConfiguration annotation that
	 * can be used on classes in src/test/java to indicate that they should not be
	 * picked up by scanning.
	 */
	@TestConfiguration
	static class UserServiceImplTestContextConfiguration {

		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
	}

	@Autowired
	UserService userService;

	@MockBean
	UserDAO userDao;

	private UserDTO createUser(Long id, int age, String name) {
		UserDTO userEntity = UserDTO.builder().id(id).name(name).age(age).build();
		return userEntity;
	}

	@Test
	public void testGetHappyPath() {
		UserDTO user = createUser(11L, 11, "User_11");
		Optional<UserDTO> userOpt = Optional.of(user);
		when(userDao.findById(11L)).thenReturn(userOpt);

		assertThat(userService.get(11L)).isEqualTo(user);
	}

	@Test(expected = InvalidUserIdException.class)
	public void testGetInvalidId() {
		Optional<UserDTO> userOpt = Optional.empty();
		when(userDao.findById(11L)).thenReturn(userOpt);
		userService.get(11L);
	}

	@Test
	public void testGetList() {
		UserDTO user1 = createUser(22L, 22, "User_22");
		UserDTO user2 = createUser(23L, 23, "User_23");
		List<UserDTO> userList = new ArrayList<UserDTO>();
		userList.add(user1);
		userList.add(user2);
		when(userDao.findAll()).thenReturn(userList);

		assertThat(userService.getList()).isEqualTo(userList);
	}

	@Test
	public void testCreate() {
		UserDTO user = createUser(33L, 33, "User_33");
		when(userDao.save(user)).thenReturn(user);
		ArgumentCaptor<UserDTO> userCaptor = ArgumentCaptor.forClass(UserDTO.class);
		userService.create(user);
		verify(userDao, times(1)).save(userCaptor.capture());
	}

	@Test
	public void testUpdateHappyPath() {
		UserDTO user = createUser(44L, 44, "User_44");
		Optional<UserDTO> userOpt = Optional.of(user);
		when(userDao.findById(44L)).thenReturn(userOpt);

		assertThat(userService.get(44L)).isEqualTo(user);
	}

	@Test(expected = InvalidUserIdException.class)
	public void testUpdateInvalidId() {
		Optional<UserDTO> userOpt = Optional.empty();
		when(userDao.findById(44L)).thenReturn(userOpt);
		userService.update(44L, UserDTO.builder().build());
	}

	@Test
	public void testDelete() {
		doNothing().when(userDao).deleteById(55L);
		ArgumentCaptor<Long> strCaptor = ArgumentCaptor.forClass(Long.class);
		userService.delete(55L);
		verify(userDao, times(1)).deleteById(strCaptor.capture());
	}

}
