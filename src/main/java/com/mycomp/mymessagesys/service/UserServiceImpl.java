package com.mycomp.mymessagesys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.repository.UserDAO;
import com.mycomp.mymessagesys.service.exceptions.InvalidUserIdException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Override
	public UserDTO get(Long id) {
		UserDTO user = null;
		Optional<UserDTO> existingUser = userDao.findById(id);
		if (existingUser.isPresent()) {
			user = existingUser.get();
		} else {
			throw new InvalidUserIdException(id);
		}
		return user;
	}

	@Override
	public List<UserDTO> getList() {
		List<UserDTO> users = userDao.findAll();
		return users;
	}

	@Override
	public void create(UserDTO newEntity) {
		userDao.save(newEntity);
	}

	@Override
	@Transactional
	public UserDTO update(Long id, UserDTO entity) throws InvalidUserIdException {
		UserDTO updatedUser = null;
		Optional<UserDTO> existingUser = userDao.findById(id);
		if (existingUser.isPresent()) {
			UserDTO usr = existingUser.get();
			usr.setName(entity.getName());
			usr.setAge(entity.getAge());
			userDao.save(usr);
			updatedUser = userDao.findById(id).get();
		} else {
			throw new InvalidUserIdException(id);
		}
		return updatedUser;
	}

	@Override
	public void delete(Long id) {
		userDao.deleteById(id);
	}
}
