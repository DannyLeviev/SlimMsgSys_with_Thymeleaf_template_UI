package com.mycomp.mymessagesys.controller;

import java.util.List;


public interface RestControllerInterface<T>{

	List<T> getList();

	void create(T newEntity);

	T get(String id);

	T update(String id, T entity);

	void delete(String id);

}