package com.capg.training.ratingservice.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDataModel {

	private Integer userId;
	private List<RatingModel> ratings;
	// add more info 
}
