package com.learn.excp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {

	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<String> exception(ProductNotFoundException exception) {
		
		return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = DuplicateProductException.class)
	public ResponseEntity<String> exception(DuplicateProductException exception) {
		
		return new ResponseEntity<String>("Duplicate product", HttpStatus.FOUND);
	}
}
