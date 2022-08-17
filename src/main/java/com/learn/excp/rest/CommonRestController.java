package com.learn.excp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Comment @ControllerAdvice first */

@RestControllerAdvice
public class CommonRestController {

	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ExceptionResponse resourceNotFoundException(ResourceNotFoundException ex) {

		return new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND, ex.getClass());
	}
}
