package com.capg.training.ratingservice.repository;

import java.util.List;

import com.capg.training.ratingservice.entity.Rating;

public interface CustomRatingJpaRepository {
	// Custom method(s)
	List<Rating> someComplexRequirement(Integer userId);
}
