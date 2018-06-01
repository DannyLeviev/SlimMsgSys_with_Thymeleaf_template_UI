package com.mycomp.mymessagesys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycomp.mymessagesys.model.MessageDTO;
import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.repository.MessageDAO;
import com.mycomp.mymessagesys.repository.UserDAO;
import com.mycomp.mymessagesys.service.exceptions.InvalidUserIdException;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDao;

	@Autowired
	private UserDAO userDao;

	@Override
	public List<MessageDTO> getList(Long userId) {
		return messageDao.findByAuthor_Id(userId);
	}

	@Override
	public MessageDTO get(Long userId, Long msgId) {
		return messageDao.findByIdAndAuthor_Id(msgId, userId);
	}

	@Override
	public void create(Long userId, MessageDTO newEntity) {
		Optional<UserDTO> userOpt = userDao.findById(userId);
		if (userOpt.isPresent()) {
			newEntity.setAuthor(userOpt.get());
			messageDao.save(newEntity);
		} else {
			throw new InvalidUserIdException(userId);
		}
	}

	@Override
	@Transactional
	public MessageDTO update(Long userId, Long msgId, MessageDTO entity) {
		MessageDTO msg = get(userId, msgId);
		if (msg != null) {
			msg.setCreationDateTime(entity.getCreationDateTime());
			msg.setText(entity.getText());
			messageDao.save(msg);
		}
		return msg;
	}

	@Override
	public void delete(Long userId, Long msgId) {
		MessageDTO msg = get(userId, msgId);
		if (msg != null) {
			messageDao.deleteById(msgId);
		}
	}

}
