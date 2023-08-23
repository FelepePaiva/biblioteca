package com.felepe.livraria.services.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Long id) {
		super("Resource not found. ID: " + id);
	}
	public ResourceNotFoundException(String string) {
		
	}

}
