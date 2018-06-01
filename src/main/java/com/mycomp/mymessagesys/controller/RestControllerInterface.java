package com.mycomp.mymessagesys.controller;

import java.util.List;


public interface RestControllerInterface<T>{

	List<T> getList(String UserId);

	T get(String UserId, String msgId);

	T update(String UserId, String msgId, T entity);
	
	void create(String UserId, T entity);

	void delete(String UserId, String msgId);

}