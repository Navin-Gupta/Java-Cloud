package com.capg.training.ratingservice.repository;

import java.util.ArrayList;
import java.util.List;

import com.capg.training.ratingservice.model.RatingModel;

public class RatingRepository {

	public static List<RatingModel> ratingList() {
		List<RatingModel> ratings = new ArrayList<RatingModel>();
		
		ratings.add(new RatingModel(1, 3));
		ratings.add(new RatingModel(2, 4));
		ratings.add(new RatingModel(3, 2));
		ratings.add(new RatingModel(4, 5));
		
		return ratings;
	}
}
