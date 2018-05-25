package com.mycomp.mymessagesys.service;

import java.util.List;

import com.mycomp.mymessagesys.model.MessageDTO;

public interface MessageService {

	List<MessageDTO> getList();

	MessageDTO get(Long id);

	void create(MessageDTO newEntity);

	MessageDTO update(Long id, MessageDTO entity);

	void delete(Long id);

}