package com.capg.training.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.capg.training.ratingservice.exception.UserNotFoundException;
import com.capg.training.ratingservice.model.ExceptionModel;
import com.capg.training.ratingservice.model.RatingDataModel;
import com.capg.training.ratingservice.model.RatingModel;
import com.capg.training.ratingservice.repository.RatingRepository;
import com.capg.training.ratingservice.service.RatingService;

@RestController
public class RatingController {

	// dependency
	@Autowired
	private RatingService service;
	
	// REST method will receive userid and return the list of RatingModel(MovieId + rating)
	// ENDPOINT : /rating/{userId}
	@GetMapping("/rating/{userId}")
	// public List<RatingModel> getRatings(@PathVariable Integer userId){
	// public RatingDataModel getRatings(@PathVariable Integer userId){
	public ResponseEntity<RatingDataModel> getRatings(@PathVariable Integer userId){
		
		// List<RatingModel> ratings =  RatingRepository.ratingList();
		
		List<RatingModel> ratings = this.service.getRatings(userId);
		// throw custom exception if rating collection is empty
		if(ratings.size() == 0) {
			throw new UserNotFoundException("User with id- " + userId + " Not Found!");
		}
		
		RatingDataModel ratingDataModel = new RatingDataModel();
		ratingDataModel.setUserId(userId);
		ratingDataModel.setRatings(ratings);
		
		/*ResponseEntity<RatingDataModel> response =
					new ResponseEntity<RatingDataModel>(<content body>, <status code>);*/
		ResponseEntity<RatingDataModel> response =
				new ResponseEntity<RatingDataModel>(ratingDataModel, HttpStatus.OK);
		
		// return ratingDataModel;
		return response;
	}
	
	/*
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
	*/
	
}
