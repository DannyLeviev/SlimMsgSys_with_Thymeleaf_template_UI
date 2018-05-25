package com.mycomp.mymessagesys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.mymessagesys.model.UserDTO;

public interface UserDAO extends JpaRepository<UserDTO, Long> {

}
