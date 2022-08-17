package com.learn.excp.rest;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

	private HttpStatus status;
	private String message;
	private Class<?> className;
	
	public ExceptionResponse(String message, HttpStatus notFound, Class<?> className) {
		this.message = message;
		this.status = notFound;
		this.className = className;
	}


	@Override
	public String toString() {
		return "{status=" + status + ", message=" + message + ", className=" + className + "}";
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
	
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Class<?> getClassName() {
		return className;
	}

	public void setClassName(Class<?> className) {
		this.className = className;
	}
}
