package com.pranav.devscribe.cutomExceptions;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
}
