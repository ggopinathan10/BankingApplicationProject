package com.business.ExceptionHandler;

public class UserAuthorizationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5733570055283689081L;

	public UserAuthorizationException(String message) {
		super(message);
	}
}
