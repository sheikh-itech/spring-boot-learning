package com.learn.excp.validation;

public class ProductDuplicacyException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProductDuplicacyException(String msg) {
		super(msg);
	}
}
