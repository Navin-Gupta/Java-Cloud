package com.capg.training.ratingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capg.training.ratingservice.entity.Rating;

@Repository
public interface RatingJpaRepository extends JpaRepository<Rating, Integer>, CustomRatingJpaRepository{
	// custom method declaration
	// correct naming convention
	// implementation will be provided internally
 	List<Rating> findRatingsByUserId(Integer userId);
 	
 	// JPQL
 	/*
 	@Query("select r.rating from rating r where r.userId =:userId and r.movieId=:movieId")
 	List<Integer> findRatingCustomWay(@Param("userId")Integer userId, @Param("movieId") Integer movieId);
 	*/
 	
 	@Query("select r from Rating r where r.userId =:userId and r.movieId=:movieId")
 	List<Rating> findRatingCustomWay(@Param("userId")Integer userId, @Param("movieId") Integer movieId);
}

/** 
 * 1. All CRUD functionality (by default)
 * 2. Naming Conventions method (implementation will be provided internally)
 * 3. Custom query
 * 4. Custom interface method implementation
 */

