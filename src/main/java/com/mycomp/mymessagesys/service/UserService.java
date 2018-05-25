package com.mycomp.mymessagesys.service;

import java.util.List;

import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.service.exceptions.InvalidUserIdException;

public interface UserService {

	UserDTO get(Long id);

	List<UserDTO> getList();

	void create(UserDTO newEntity);

	UserDTO update(Long id, UserDTO entity) throws InvalidUserIdException;

	void delete(Long id);

}