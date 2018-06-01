package com.mycomp.mymessagesys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycomp.mymessagesys.model.CommentDTO;
import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.repository.CommentDAO;
import com.mycomp.mymessagesys.repository.UserDAO;
import com.mycomp.mymessagesys.service.exceptions.InvalidUserIdException;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDAO cmntDao;

	@Autowired
	private UserDAO userDao;

	@Override
	public List<CommentDTO> getMessageComments(Long userId, Long msgId) {
		List<CommentDTO> cmntsList = cmntDao.findByAuthor_IdAndParentMsgId(userId, msgId);
		return cmntsList;
	}

	@Override
	public void createMessageComment(Long userId, Long msgId, CommentDTO newComnt) {
		Optional<UserDTO> userOpt = userDao.findById(userId);
		if (userOpt.isPresent()) {
			newComnt.setAuthor(userOpt.get());
			cmntDao.save(newComnt);
		} else {
			throw new InvalidUserIdException(userId);
		}
	}

}
