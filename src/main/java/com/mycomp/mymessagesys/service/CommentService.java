package com.mycomp.mymessagesys.service;

import java.util.List;

import com.mycomp.mymessagesys.model.CommentDTO;

public interface CommentService {

	List<CommentDTO> getMessageComments(Long userId, Long msgId);

	void createMessageComment(Long userId, Long msgId, CommentDTO newComnt);

}