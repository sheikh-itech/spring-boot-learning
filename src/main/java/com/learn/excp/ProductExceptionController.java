package com.learn.excp;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learn.excp.validation.CustomFieldsException;
import com.learn.excp.validation.ProductDuplicacyException;

@ControllerAdvice
public class ProductExceptionController {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exception(Exception exception) {
		
		return new ResponseEntity<String>("Internal server error!!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = DuplicateLearningTech.class)
	public ResponseEntity<String> exception(DuplicateLearningTech exception) {
		
		return new ResponseEntity<String>("Duplicate LearningTech", HttpStatus.FOUND);
	}
	
	@ExceptionHandler(value = LearningTechNotPresent.class)
	public ResponseEntity<String> exception(LearningTechNotPresent exception) {
		
		return new ResponseEntity<String>("LearningTech Not Found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<String> exception(ProductNotFoundException exception) {
		
		return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = DuplicateProductException.class)
	public ResponseEntity<String> exception(DuplicateProductException exception) {
		
		return new ResponseEntity<String>("Duplicate product", HttpStatus.FOUND);
	}
	
	@ExceptionHandler(value = ProductDuplicacyException.class)
	public ResponseEntity<String> exception(ProductDuplicacyException exception) {
		
		return new ResponseEntity<String>("Product duplicasy issue", HttpStatus.FOUND);
	}
	
	@ExceptionHandler(value = CustomFieldsException.class)
	public ResponseEntity<String> exception(CustomFieldsException exception) {
		
		return new ResponseEntity<String>("Field validation issue", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<String> exception(ConstraintViolationException exception) {
		
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
