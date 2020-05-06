package com.capg.training.ratingservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capg.training.ratingservice.entity.Rating;

public class CustomRatingJpaRepositoryImpl implements CustomRatingJpaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Rating> someComplexRequirement(Integer userId) {
		// Custom code for DB interaction
		return null;
	}

}
