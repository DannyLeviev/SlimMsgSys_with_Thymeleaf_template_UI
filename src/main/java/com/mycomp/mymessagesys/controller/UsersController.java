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

import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UserDTO> getList() {
		List<UserDTO> users = userService.getList();
		return users;
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO get(@PathVariable("id") String id) {
		UserDTO usr = userService.get(Long.parseLong(id, 10));
		return usr;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody UserDTO newEntity) {
		userService.create(newEntity);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO update(@PathVariable("id") String id, @RequestBody UserDTO entity) {
		UserDTO userDto = userService.update(Long.parseLong(id, 10), entity);
		return userDto;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		userService.delete(Long.parseLong(id, 10));
	}
}
