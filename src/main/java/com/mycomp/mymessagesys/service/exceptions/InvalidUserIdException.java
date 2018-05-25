package com.mycomp.mymessagesys.service.exceptions;

public class InvalidUserIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;


	public InvalidUserIdException(Long userId) {
		super("There is not User with id = " + userId);
	}
}
