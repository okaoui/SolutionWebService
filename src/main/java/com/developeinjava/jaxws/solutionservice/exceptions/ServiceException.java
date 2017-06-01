package com.developeinjava.jaxws.solutionservice.exceptions;


public class ServiceException extends Exception {

	private static final long serialVersionUID = 3403167971694083224L;
	
	
	public ServiceException(){
		
	}
	
	public ServiceException(String message){
		super(message);
	}

	
	
	public ServiceException(Throwable cause){
		super(cause);
	}
	
	public ServiceException(String message, Throwable cause){
		super(message,cause);
	}

	

	
	
}
