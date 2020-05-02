package com.capg.training.ratingservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.capg.training.ratingservice.model.RatingDataModel;
import com.capg.training.ratingservice.model.RatingModel;
import com.capg.training.ratingservice.repository.RatingRepository;

@RestController
public class RatingController {

	// REST method will receive userid and return the list of RatingModel(MovieId + rating)
	// ENDPOINT : /rating/{userId}
	@GetMapping("/rating/{userId}")
	// public List<RatingModel> getRatings(@PathVariable Integer userId){
	// public RatingDataModel getRatings(@PathVariable Integer userId){
	public ResponseEntity<RatingDataModel> getRatings(@PathVariable Integer userId){
		
		List<RatingModel> ratings =  RatingRepository.ratingList();
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
}
