package com.mycomp.mymessagesys.service.unit_tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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

import com.mycomp.mymessagesys.model.MessageDTO;
import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.repository.MessageDAO;
import com.mycomp.mymessagesys.service.MessageService;
import com.mycomp.mymessagesys.service.MessageServiceImpl;
import com.mycomp.mymessagesys.service.exceptions.InvalidMessageIdException;

@RunWith(SpringRunner.class)
public class MessageServiceImplTest {

	/*
	 * During component scanning, we might find components or configurations
	 * (created only for specific tests) accidentally get picked up everywhere. To
	 * help prevent that, Spring Boot provides @TestConfiguration annotation that
	 * can be used on classes in src/test/java to indicate that they should not be
	 * picked up by scanning.
	 */
	@TestConfiguration
	static class MessageServiceImplTestContextConfiguration {

		@Bean
		public MessageService messageService() {
			return new MessageServiceImpl();
		}
	}

	@Autowired
	MessageService msgService;

	@MockBean
	MessageDAO msgDao;

	private MessageDTO createMessage(Long id, Long authorId, String txt) {
		UserDTO author = UserDTO.builder().id(authorId).name("author").age(120).build();
		MessageDTO msg = MessageDTO.msg_builder().id(id).author(author).text(txt)
				.creationDateTime(LocalDateTime.now().toString()).build();
		return msg;
	}

	@Test
	public void testGetHappyPath() {
		MessageDTO msg = createMessage(11L, 11L, "Message_11");
		Optional<MessageDTO> msgOpt = Optional.of(msg);
		when(msgDao.findById(11L)).thenReturn(msgOpt);

		assertThat(msgService.get(11L, 11L)).isEqualTo(msg);
	}

	@Test(expected = InvalidMessageIdException.class)
	public void testGetInvalidId() {
		Optional<MessageDTO> msgOpt = Optional.empty();
		when(msgDao.findById(12L)).thenReturn(msgOpt);
		msgService.get(11L, 12L);
	}

	@Test
	public void testGetList() {
		MessageDTO msg1 = createMessage(22L, 22L, "Message_22");
		MessageDTO msg2 = createMessage(22L, 22L, "Message_22");
		List<MessageDTO> msgList = new ArrayList<MessageDTO>();
		msgList.add(msg1);
		msgList.add(msg2);
		when(msgDao.findAll()).thenReturn(msgList);

		assertThat(msgService.getList(22L)).isEqualTo(msgList);
	}

	@Test
	public void testCreate() {
		MessageDTO msg = createMessage(33L, 33L, "Message_33");
		when(msgDao.save(msg)).thenReturn(msg);
		ArgumentCaptor<MessageDTO> msgCaptor = ArgumentCaptor.forClass(MessageDTO.class);
		msgService.create(33L, msg);
		verify(msgDao, times(1)).save(msgCaptor.capture());
	}

	@Test
	public void testUpdateHappyPath() {
		MessageDTO msg = createMessage(44L, 44L, "Message_44");
		Optional<MessageDTO> msgOpt = Optional.of(msg);
		when(msgDao.findById(44L)).thenReturn(msgOpt);

		assertThat(msgService.get(44L, 44L)).isEqualTo(msg);
	}

	@Test
	public void testDelete() {
		doNothing().when(msgDao).deleteById(55L);
		MessageDTO msg = createMessage(55L, 55L, "Message_55");
		when(msgService.get(55L, 55L)).thenReturn(msg);
		ArgumentCaptor<Long> strCaptor = ArgumentCaptor.forClass(Long.class);
		msgService.delete(55L, 55L);
		verify(msgDao, times(1)).deleteById(strCaptor.capture());
	}

}
