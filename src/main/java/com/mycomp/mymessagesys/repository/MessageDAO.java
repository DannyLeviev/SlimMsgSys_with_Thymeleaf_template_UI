package com.mycomp.mymessagesys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.mymessagesys.model.MessageDTO;

public interface MessageDAO extends JpaRepository<MessageDTO, Long> {

}
