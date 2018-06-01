package com.mycomp.mymessagesys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.mymessagesys.model.MessageDTO;

public interface MessageDAO extends JpaRepository<MessageDTO, Long> {

	/*
	 * Query builder mechanism built into Spring Data repository infrastructure
	 * is useful for building constraining queries over entities of entities of the repository.
	 * See documentation: 
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-property-expressions
	 */
	
	public List<MessageDTO> findByAuthor_Id(Long userId);
	
	public MessageDTO findByIdAndAuthor_Id(Long msgId, Long userId);
}
