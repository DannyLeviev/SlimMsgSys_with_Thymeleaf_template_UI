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
@RequestMapping("/api/messages")
public class MessageController implements RestControllerInterface<MessageDTO> {

	@Autowired
	private MessageService msgService;

	@Override
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<MessageDTO> getList() {
		List<MessageDTO> msgList = msgService.getList();
		return msgList;
	}

	@Override
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public MessageDTO get(@PathVariable("id") String id) {
		MessageDTO msg = msgService.get(Long.parseLong(id, 10));
		return msg;
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody MessageDTO newEntity) {
		msgService.create(newEntity);
	}

	@Override
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public MessageDTO update(@PathVariable("id") String id, @RequestBody MessageDTO entity) {
		MessageDTO updatedmsg = msgService.update(Long.parseLong(id, 10), entity);
		return updatedmsg;
	}

	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		msgService.delete(Long.parseLong(id, 10));

	}

}
