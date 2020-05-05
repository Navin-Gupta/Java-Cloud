package com.capg.training.ratingservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.training.ratingservice.entity.Rating;
import com.capg.training.ratingservice.model.RatingModel;
import com.capg.training.ratingservice.repository.RatingJpaRepository;

@Service
public class RatingService {

	@Autowired
	private RatingJpaRepository repository;
	
	
 	public List<RatingModel> getRatings(Integer userId){
 		
 		List<Rating> ratings =  this.repository.findRatingsByUserId(userId);
 		List<RatingModel> ratingModels = ratings.stream()
 			.map(rating -> {
 				RatingModel ratingModel = new RatingModel(rating.getMovieId(), rating.getRating());
 				return ratingModel;
 			})
 			.collect(Collectors.toList());
 		return ratingModels;
 	}
}
