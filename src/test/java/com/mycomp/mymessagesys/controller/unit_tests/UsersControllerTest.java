package com.mycomp.mymessagesys.controller.unit_tests;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycomp.mymessagesys.controller.UsersController;
import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest {

	private final String URL = "/api/users";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper jacksonMapper;

	@MockBean
	private UserServiceImpl userService;

	private UserDTO createUser(Long id, int age, String name) {
		UserDTO userEntity = UserDTO.builder().id(id).age(age).name(name).build();
		return userEntity;
	}

	@Test
	public void testGetList() throws Exception {
		List<UserDTO> usersList = new ArrayList<>();
		usersList.add(createUser(111L, 11, "User_1"));
		usersList.add(createUser(222L, 22, "User_2"));
		usersList.add(createUser(333L, 33, "User_3"));
		when(userService.getList()).thenReturn(usersList);
		this.mockMvc.perform(get(URL)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().string(jacksonMapper.writeValueAsString(usersList)));
		verify(userService, times(1)).getList();
	}

	@Test
	public void testGet() throws Exception {
		UserDTO userEntity = createUser(555L, 55, "User_5");
		when(userService.get(555L)).thenReturn(userEntity);
		this.mockMvc.perform(get(URL + "/555")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().string(jacksonMapper.writeValueAsString(userEntity)));
		verify(userService, times(1)).get(555L);
	}

	@Test
	public void testCreate() throws Exception {
		UserDTO userEntity = createUser(444L, 44, "User_4");
		doNothing().when(userService).create(userEntity);
		this.mockMvc.perform(
				post(URL).contentType(MediaType.APPLICATION_JSON).content(jacksonMapper.writeValueAsString(userEntity)))
				.andExpect(status().isCreated());
		ArgumentCaptor<UserDTO> dtoCaptor = ArgumentCaptor.forClass(UserDTO.class);
		verify(userService, times(1)).create(dtoCaptor.capture());
	}

	@Test
	public void testUpdate() throws Exception {
		UserDTO userBeforeUpdate = createUser(666L, 66, "User_6");
		UserDTO userAfterUpdate = createUser(655L, 66, "User_6");
		when(userService.update(666L, userBeforeUpdate)).thenReturn(userAfterUpdate);
		this.mockMvc
				.perform(put(URL + "/{id}", "666").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(jacksonMapper.writeValueAsString(userBeforeUpdate)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk())
				.andExpect(content().string(jacksonMapper.writeValueAsString(userAfterUpdate)));
		ArgumentCaptor<Long> strCaptor = ArgumentCaptor.forClass(Long.class);
		ArgumentCaptor<UserDTO> dtoCaptor = ArgumentCaptor.forClass(UserDTO.class);
		verify(userService, times(1)).update(strCaptor.capture(), dtoCaptor.capture());
	}

	@Test
	public void testDelete() throws Exception {
		doNothing().when(userService).delete(777L);
		this.mockMvc.perform(delete(URL + "/777").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		verify(userService, times(1)).delete(777L);
	}

}
