package com.mycomp.mymessagesys.service.exceptions;

public class InvalidMessageIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;


	public InvalidMessageIdException(Long msgId) {
		super("There is not Message with id = " + msgId);
	}
}
