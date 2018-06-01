package com.mycomp.mymessagesys.service;

import java.util.List;

import com.mycomp.mymessagesys.model.MessageDTO;

public interface MessageService {

	List<MessageDTO> getList(Long userId);

	MessageDTO get(Long userId, Long msgId);

	void create(Long userId, MessageDTO newEntity);

	MessageDTO update(Long userId, Long msgId, MessageDTO entity);

	void delete(Long userId, Long id);

}