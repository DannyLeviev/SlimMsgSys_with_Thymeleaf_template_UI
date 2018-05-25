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
@RequestMapping("/api/messages/{id}/comments")
public class CommentController {

	@Autowired
	CommentService cmntService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDTO> getList(@PathVariable("id") String id) {
		List<CommentDTO> cmnts = cmntService.getMessageComments(Long.parseLong(id, 10));
		return cmnts;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createMessageComment(@PathVariable("id") String id, @RequestBody CommentDTO newComnt) {
		cmntService.createMessageComment(Long.parseLong(id, 10), newComnt);
	}

}
