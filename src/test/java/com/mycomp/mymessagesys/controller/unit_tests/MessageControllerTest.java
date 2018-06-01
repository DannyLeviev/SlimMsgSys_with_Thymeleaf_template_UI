package com.mycomp.mymessagesys.controller.unit_tests;

import static org.junit.Assert.assertEquals;
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

import java.time.LocalDateTime;
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
import com.mycomp.mymessagesys.controller.MessageController;
import com.mycomp.mymessagesys.model.MessageDTO;
import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.service.MessageServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

	private static String URL = "/api/users/{userId}/messages";

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper jacksonMapper;

	@MockBean
	MessageServiceImpl msgService;

	private MessageDTO createMessage(Long id, Long authorId, String txt) {
		UserDTO author = UserDTO.builder().id(authorId).name("author").age(120).build();
		MessageDTO msg = MessageDTO.msg_builder().id(id).author(author).text(txt)
				.creationDateTime(LocalDateTime.now().toString()).build();
		return msg;
	}

	@Test
	public void testGetList() throws Exception {
		List<MessageDTO> msgList = new ArrayList<>();
		msgList.add(createMessage(11L, 111L, "This is a message text!!!"));
		when(msgService.getList(111L)).thenReturn(msgList);
		mockMvc.perform(get(URL, "111")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().string(jacksonMapper.writeValueAsString(msgList)));
		verify(msgService, times(1)).getList(111L);
	}

	@Test
	public void testGet() throws Exception {
		MessageDTO msg = createMessage(22L, 222L, "This is a message text!!!");
		when(msgService.get(222L, 22L)).thenReturn(msg);
		mockMvc.perform(get(URL + "/{id}","222", "22")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().string(jacksonMapper.writeValueAsString(msg)));
		verify(msgService, times(1)).get(222L, 22L);
	}

	@Test
	public void testCreate() throws Exception {
		MessageDTO createdMsg = createMessage(33L, 333L, "This is a message text!!!");
		doNothing().when(msgService).create(333L, createdMsg);
		mockMvc.perform(post(URL, "333").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(jacksonMapper.writeValueAsBytes(createdMsg))).andExpect(status().isCreated());
		ArgumentCaptor<MessageDTO> msgDtoCaptor = ArgumentCaptor.forClass(MessageDTO.class);
		ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
		verify(msgService, times(1)).create(idCaptor.capture(), msgDtoCaptor.capture());
	}

	@Test
	public void testUpdate() throws Exception {
		MessageDTO msgBeforeUpdate = createMessage(44L, 444L, "This is a message text!!!");
		MessageDTO msgAfterUpdate = createMessage(45L, 444L, "This is a message text!!!");
		msgAfterUpdate.setCreationDateTime(msgBeforeUpdate.getCreationDateTime());
		when(msgService.update(444L, 44L, msgBeforeUpdate)).thenReturn(msgAfterUpdate);
		this.mockMvc
				.perform(put(URL + "/{id}","444", "44").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(jacksonMapper.writeValueAsBytes(msgBeforeUpdate)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk())
				.andExpect(content().string(jacksonMapper.writeValueAsString(msgAfterUpdate)));
		ArgumentCaptor<MessageDTO> msgDtoCaptor = ArgumentCaptor.forClass(MessageDTO.class);
		ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
		verify(msgService, times(1)).update(idCaptor.capture(), idCaptor.capture(), msgDtoCaptor.capture());
				
		List<Long> idList = idCaptor.getAllValues();
		assertEquals("222", idList.get(0).toString());
		assertEquals("333", idList.get(1).toString());
		assertEquals(msgAfterUpdate.getId(), msgDtoCaptor.getValue().getId());
	}

	@Test
	public void testDelete() throws Exception {
		doNothing().when(msgService).delete(555L, 55L);
		mockMvc.perform(delete(URL + "/{id}","555", "55").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNoContent());
		verify(msgService, times(1)).delete(555L, 55L);
	}

}
