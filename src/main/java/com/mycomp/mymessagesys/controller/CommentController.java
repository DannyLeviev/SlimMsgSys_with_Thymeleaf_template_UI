package com.mycomp.mymessagesys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mycomp.mymessagesys.model.CommentDTO;
import com.mycomp.mymessagesys.service.CommentService;

@RestController
@RequestMapping("/api/users/{userId}/messages/{msgId}/comments")
public class CommentController {

	@Autowired
	CommentService cmntService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDTO> getList(@PathVariable("userId") String userId, @PathVariable("msgId") String msgId) {
		List<CommentDTO> cmnts = cmntService.getMessageComments(Long.parseLong(userId, 10), Long.parseLong(msgId, 10));
		return cmnts;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createMessageComment(@PathVariable("userId") String userId, @PathVariable("msgId") String msgId,
			@RequestBody CommentDTO newComnt) {
		cmntService.createMessageComment(Long.parseLong(userId, 10), Long.parseLong(msgId, 10), newComnt);
	}

}
