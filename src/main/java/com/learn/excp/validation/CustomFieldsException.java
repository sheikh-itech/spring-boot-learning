package com.learn.excp.validation;

public class CustomFieldsException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomFieldsException(String msg) {
		super(msg);
	}
	
	public static String fieldNotFound(int id) {
		return "field "+id+" not found";
	}
	
	public static String fieldAlreadyExists() {
		return "Already exists with given name";
	}
}
