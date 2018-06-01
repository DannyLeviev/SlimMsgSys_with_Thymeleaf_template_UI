package com.mycomp.mymessagesys.service.unit_tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycomp.mymessagesys.model.CommentDTO;
import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.repository.CommentDAO;
import com.mycomp.mymessagesys.service.CommentService;
import com.mycomp.mymessagesys.service.CommentServiceImpl;

@RunWith(SpringRunner.class)
public class CommentServiceImplTest {

	/*
	 * During component scanning, we might find components or configurations
	 * (created only for specific tests) accidentally get picked up everywhere. To
	 * help prevent that, Spring Boot provides @TestConfiguration annotation that
	 * can be used on classes in src/test/java to indicate that they should not be
	 * picked up by scanning.
	 */
	@TestConfiguration
	static class CommentServiceImplTestContextConfiguration {

		@Bean
		public CommentService commentService() {
			return new CommentServiceImpl();
		}
	}

	@Autowired
	CommentService cmntService;

	@MockBean
	CommentDAO cmntDao;

	private CommentDTO createComment(Long id, Long authorId, String txt, Long parentMsgId) {
		UserDTO author = UserDTO.builder().id(authorId).name("author").age(120).build();
		CommentDTO cmnt = CommentDTO.cmnt_builder().id(id).author(author).text(txt)
				.creationDateTime(LocalDateTime.now().toString()).parentMsgId(parentMsgId).build();
		return cmnt;
	}

	@Test
	public void testGetMessageComments() {
		CommentDTO cmnt1 = createComment(11L, 11L, "Comment_11", 111L);
		CommentDTO cmnt2 = createComment(22L, 22L, "Comment_22", 111L);
		List<CommentDTO> cmntList = new ArrayList<CommentDTO>();
		cmntList.add(cmnt1);
		cmntList.add(cmnt2);
		when(cmntDao.findByAuthor_IdAndParentMsgId(11L, 111L)).thenReturn(cmntList);

		assertThat(cmntService.getMessageComments(11L, 111L)).isEqualTo(cmntList);
	}

	@Test
	public void testCreateMessageComment() {
		CommentDTO cmnt1 = createComment(33L, 33L, "Comment_33", 333L);
		when(cmntDao.save(cmnt1)).thenReturn(cmnt1);
		ArgumentCaptor<CommentDTO> cmntCaptor = ArgumentCaptor.forClass(CommentDTO.class);
		cmntService.createMessageComment(33L, 333L, cmnt1);
		verify(cmntDao, times(1)).save(cmntCaptor.capture());
	}

}
