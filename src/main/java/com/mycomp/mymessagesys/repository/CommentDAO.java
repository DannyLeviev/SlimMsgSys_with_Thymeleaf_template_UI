package com.mycomp.mymessagesys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.mymessagesys.model.CommentDTO;

public interface CommentDAO extends JpaRepository<CommentDTO, Long> {

	/*
	 * Query builder mechanism built into Spring Data repository infrastructure
	 * is useful for building constraining queries over entities of the repository.
	 * See documentation: 
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
	 */
	public List<CommentDTO> findByParentMsgId(Long parentMsgId);

}
