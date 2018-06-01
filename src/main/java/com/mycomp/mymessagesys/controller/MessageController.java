package com.mycomp.mymessagesys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mycomp.mymessagesys.model.MessageDTO;
import com.mycomp.mymessagesys.service.MessageService;

@RestController
@RequestMapping("/api/users/{userId}/messages")
public class MessageController implements RestControllerInterface<MessageDTO> {

	@Autowired
	private MessageService msgService;

	@Override
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<MessageDTO> getList(@PathVariable("userId") String userId) {
		List<MessageDTO> msgList = msgService.getList(Long.parseLong(userId, 10));
		return msgList;
	}

	@Override
	@GetMapping("/{msgId}")
	@ResponseStatus(HttpStatus.OK)
	public MessageDTO get(@PathVariable("userId") String userId, @PathVariable("msgId") String msgId) {
		MessageDTO msg = msgService.get(Long.parseLong(userId, 10), Long.parseLong(msgId, 10));
		return msg;
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@PathVariable("userId") String userId, @RequestBody MessageDTO newEntity) {
		msgService.create(Long.parseLong(userId, 10), newEntity);
	}

	@Override
	@PutMapping("/{msgId}")
	@ResponseStatus(HttpStatus.OK)
	public MessageDTO update(@PathVariable("userId") String userId, @PathVariable("msgId") String msgId,
			@RequestBody MessageDTO entity) {
		MessageDTO updatedmsg = msgService.update(Long.parseLong(userId, 10), Long.parseLong(msgId, 10), entity);
		return updatedmsg;
	}

	@Override
	@DeleteMapping("/{msgId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("userId") String userId, @PathVariable("msgId") String msgId) {
		msgService.delete(Long.parseLong(userId, 10), Long.parseLong(msgId, 10));

	}

}
