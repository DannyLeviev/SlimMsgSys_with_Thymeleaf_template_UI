package com.mycomp.mymessagesys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycomp.mymessagesys.model.CommentDTO;
import com.mycomp.mymessagesys.repository.CommentDAO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDAO cmntDao;

	@Override
	public List<CommentDTO> getMessageComments(Long id) {
		List<CommentDTO> cmntsList = cmntDao.findByParentMsgId(id);
		return cmntsList;
	}

	@Override
	public void createMessageComment(Long id, CommentDTO newComnt) {
		if (id.equals(newComnt.getParentMsgId())) {
			cmntDao.save(newComnt);
		}
	}

}
