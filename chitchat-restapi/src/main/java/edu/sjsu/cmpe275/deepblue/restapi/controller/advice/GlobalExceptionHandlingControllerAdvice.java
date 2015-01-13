package edu.sjsu.cmpe275.deepblue.restapi.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.sjsu.cmpe275.deepblue.service.exception.InvalidIdentifierException;

@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid identifier")
	@ExceptionHandler(value = { InvalidIdentifierException.class })
	public void invalidIdentifier() {
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Illegal State")
	@ExceptionHandler(value = { IllegalStateException.class })
	public void illegalState() {
	}
}