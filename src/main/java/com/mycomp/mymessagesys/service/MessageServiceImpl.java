package com.mycomp.mymessagesys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycomp.mymessagesys.model.MessageDTO;
import com.mycomp.mymessagesys.repository.MessageDAO;
import com.mycomp.mymessagesys.service.exceptions.InvalidMessageIdException;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDao;

	@Override
	public List<MessageDTO> getList() {
		List<MessageDTO> msgList = messageDao.findAll();
		return msgList;
	}

	@Override
	public MessageDTO get(Long id) {
		MessageDTO msg = null;
		Optional<MessageDTO> optionalMsg = messageDao.findById(id);
		if (optionalMsg.isPresent()) {
			msg = optionalMsg.get();
		} else {
			throw new InvalidMessageIdException(id);
		}
		return msg;
	}

	@Override
	public void create(MessageDTO newEntity) {
		messageDao.save(newEntity);
	}

	@Override
	@Transactional
	public MessageDTO update(Long id, MessageDTO entity) {
		MessageDTO msg = null;
		Optional<MessageDTO> optionalMsg = messageDao.findById(id);
		if (optionalMsg.isPresent()) {
			msg = optionalMsg.get();
			msg.setAuthorId(entity.getAuthorId());
			msg.setCreationDateTime(entity.getCreationDateTime());
			msg.setText(entity.getText());
			messageDao.save(msg);
		} else {
			throw new InvalidMessageIdException(id);
		}
		return msg;
	}

	@Override
	public void delete(Long id) {
		messageDao.deleteById(id);

	}

}
