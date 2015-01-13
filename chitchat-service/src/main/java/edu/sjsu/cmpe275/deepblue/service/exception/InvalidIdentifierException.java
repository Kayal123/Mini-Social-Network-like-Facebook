package edu.sjsu.cmpe275.deepblue.service.exception;

public class InvalidIdentifierException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidIdentifierException() {
	}
	
	public InvalidIdentifierException(String message) {
		super(message);
	}
}
