package com.task.user.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException{

	
	public UserNotFoundException(String message){
		super(message);
	}

}
