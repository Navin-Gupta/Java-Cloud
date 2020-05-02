package com.capg.training.movieservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.capg.training.movieservice.model.MovieModel;


@RestController
public class MovieController {

	// every class of spring boot application is injected with bean : Environment
	@Autowired
	private Environment env;
	
	// REST method that will receive movieId an return movie details
	// ENDPOINT : /movies/{movieId}
	@GetMapping("/movie/{movieId}")
	public ResponseEntity<MovieModel> movieDetail(@PathVariable Integer movieId) {
		// dummy MovieModel Object
		// to fetch the port number on which current MS is running
		String port = this.env.getProperty("server.port");
		MovieModel movieModel = new MovieModel("Movie-" + movieId + "(" + port + ")", "Action");
		
		ResponseEntity<MovieModel> response =
					new ResponseEntity<MovieModel>(movieModel, HttpStatus.OK);
		
		return response;
	}
}
