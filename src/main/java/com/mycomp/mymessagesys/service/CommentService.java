package com.mycomp.mymessagesys.service;

import java.util.List;

import com.mycomp.mymessagesys.model.CommentDTO;

public interface CommentService {

	List<CommentDTO> getMessageComments(Long id);

	void createMessageComment(CommentDTO newComnt);

}