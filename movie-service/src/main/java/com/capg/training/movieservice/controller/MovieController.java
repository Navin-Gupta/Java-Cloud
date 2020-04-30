package com.capg.training.movieservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.capg.training.movieservice.model.MovieModel;


@RestController
public class MovieController {

	// REST method that will receive movieId an return movie details
	// ENDPOINT : /movies/{movieId}
	@GetMapping("/movie/{movieId}")
	public MovieModel movieDetail(@PathVariable Integer movieId) {
		// dummy MovieModel Object
		MovieModel movieModel = new MovieModel("Movie-" + movieId, "Action");
		
		return movieModel;
	}
}
