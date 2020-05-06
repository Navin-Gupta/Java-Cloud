package com.capg.training.ratingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capg.training.ratingservice.model.ExceptionModel;

// can handle exception arising in any method of any controller
@RestControllerAdvice
public class GlobalHandler {

	// Spring Exception Handler
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionModel> UserNotFoundExceptionHandler(UserNotFoundException ex) {
		// logic to handle exception
		ExceptionModel exceptionModel  = new ExceptionModel(ex.getMessage(), 
													   HttpStatus.NOT_FOUND.value(), 
													   System.currentTimeMillis());
		ResponseEntity<ExceptionModel> response =
					new ResponseEntity<ExceptionModel>(exceptionModel, HttpStatus.NOT_FOUND);
		
		return response;
		
	}
	
	// Spring Exception Handler
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionModel>   ExceptionHandler(Exception ex) {
		// logic to handle exception
		ExceptionModel exceptionModel  = new ExceptionModel(ex.getMessage(), 
									   HttpStatus.BAD_REQUEST.value(), 
									   System.currentTimeMillis());
		ResponseEntity<ExceptionModel> response =
		new ResponseEntity<ExceptionModel>(exceptionModel, HttpStatus.BAD_REQUEST);
		
		return response;
	}
}
