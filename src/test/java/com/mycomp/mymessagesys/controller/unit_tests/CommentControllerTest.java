package com.mycomp.mymessagesys.controller.unit_tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.mycomp.mymessagesys.controller.CommentController;
import com.mycomp.mymessagesys.model.CommentDTO;
import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.service.CommentServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

	private static String URL = "/api/users/{userId}/messages/{msgId}/comments";


	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper jacksonMapper;

	@MockBean
	CommentServiceImpl cmntService;

	private CommentDTO createComment(Long id, Long authorId, String txt, Long msgId) {
		UserDTO author = UserDTO.builder().id(authorId).name("author").age(120).build();
		CommentDTO newCmnt = CommentDTO.cmnt_builder().id(id).author(author).text(txt)
				.creationDateTime(LocalDateTime.now().toString()).parentMsgId(msgId).build();
		return newCmnt;
	}

	@Test
	public void testGetList() throws Exception {
		CommentDTO newCmnt = createComment(11L, 22L, "This is a new Comment !", 33L);
		List<CommentDTO> cmntList = new ArrayList<>();
		cmntList.add(newCmnt);
		when(cmntService.getMessageComments(22L, 33L)).thenReturn(cmntList);
		mockMvc.perform(get(URL, "22", "33")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andExpect(content().string(jacksonMapper.writeValueAsString(cmntList)));
		verify(cmntService, times(1)).getMessageComments(22L, 33L);
	}

	@Test
	public void testCreateMessageComment() throws Exception {
		CommentDTO newCmnt = createComment(111L, 222L, "This is a created Comment !", 333L);
		doNothing().when(cmntService).createMessageComment(222L, 333L, newCmnt);
		mockMvc.perform(post(URL, "222", "333").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(jacksonMapper.writeValueAsBytes(newCmnt))).andExpect(status().isCreated());
		ArgumentCaptor<CommentDTO> cmntCaptor = ArgumentCaptor.forClass(CommentDTO.class);
		ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
		verify(cmntService, times(1)).createMessageComment(idCaptor.capture(), idCaptor.capture(),
				cmntCaptor.capture());

		List<Long> idList = idCaptor.getAllValues();
		assertEquals("222", idList.get(0).toString());
		assertEquals("333", idList.get(1).toString());
		assertEquals(newCmnt.getId(), cmntCaptor.getValue().getId());


	}

}
