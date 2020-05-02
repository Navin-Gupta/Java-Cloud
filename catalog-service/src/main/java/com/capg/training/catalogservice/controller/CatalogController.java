package com.capg.training.catalogservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capg.training.catalogservice.feignproxy.MovieServiceProxy;
import com.capg.training.catalogservice.feignproxy.RatingServiceProxy;
import com.capg.training.catalogservice.model.CatalogDataModel;
import com.capg.training.catalogservice.model.CatalogModel;
import com.capg.training.catalogservice.model.MovieModel;
import com.capg.training.catalogservice.model.RatingDataModel;
import com.capg.training.catalogservice.model.RatingModel;

@RestController
public class CatalogController {

	// dependency
	@Autowired
	private RestTemplate restTemplate;
	
	// Depenedency of proxy
	@Autowired
	private MovieServiceProxy movieProxy;
	
	@Autowired
	private RatingServiceProxy ratingProxy;
	
	
	// REST endpoints for other MS
	// private final String RATING_URL = "http://localhost:6060/rating-service/rating";
	// private final String MOVIE_URL = "http://localhost:7070/movie-service/movie";
	
	// using Discovery Server
	private final String RATING_URL = "http://rating-service/rating";
	private final String MOVIE_URL = "http://movie-service/movie";
	
	// REST method receives userId and returns list of CatalogModel
	// ENDPOINT : /catalog/{userId}
	@GetMapping("/catalog/{userId}")
	// public List<CatalogModel> getCatalog(@PathVariable Integer userId){
	public CatalogDataModel getCatalog(@PathVariable Integer userId){
		
		List<CatalogModel> catalogList = new ArrayList<CatalogModel>();
		
		// 1. generate a request to Rating Service
		RatingDataModel ratingDataModel = this.restTemplate.getForObject(this.RATING_URL + "/" + userId, RatingDataModel.class);
		
		// 2. generate request(s) to Movie Service
		// iterate through list from ratingDataModel and calling movieservice for each movieId
		for(RatingModel ratingModel : ratingDataModel.getRatings()) {
			Integer movieId = ratingModel.getMovieId();
			MovieModel movieModel = this.restTemplate.getForObject(this.MOVIE_URL + "/" + movieId, MovieModel.class);
			
			// create an instance of catalog model
			CatalogModel catalogModel = new CatalogModel(movieId, 
														 ratingModel.getRating(),
														 movieModel.getMovieName(), 
														 movieModel.getCategory());
			catalogList.add(catalogModel);
			
		}
		
		CatalogDataModel catalogDataModel = new CatalogDataModel();
		catalogDataModel.setUserId(userId);
		catalogDataModel.setCatalogList(catalogList);
		
		return catalogDataModel;
	}
	
	@GetMapping("/catalog-feign/{userId}")
	public ResponseEntity<CatalogDataModel> getCatalogFeign(@PathVariable Integer userId){
		
		List<CatalogModel> catalogList = new ArrayList<CatalogModel>();
		
		// 1. generate a request to Rating Service
		// RatingDataModel ratingDataModel = this.restTemplate.getForObject(this.RATING_URL + "/" + userId, RatingDataModel.class);
		RatingDataModel ratingDataModel = this.ratingProxy.getRatings(userId);
		
		// 2. generate request(s) to Movie Service
		// iterate through list from ratingDataModel and calling movieservice for each movieId
		for(RatingModel ratingModel : ratingDataModel.getRatings()) {
			Integer movieId = ratingModel.getMovieId();
			// MovieModel movieModel = this.restTemplate.getForObject(this.MOVIE_URL + "/" + movieId, MovieModel.class);
			MovieModel movieModel = this.movieProxy.movieDetail(movieId);
			
			// create an instance of catalog model
			CatalogModel catalogModel = new CatalogModel(movieId, 
														 ratingModel.getRating(),
														 movieModel.getMovieName(), 
														 movieModel.getCategory());
			catalogList.add(catalogModel);
			
		}
		
		CatalogDataModel catalogDataModel = new CatalogDataModel();
		catalogDataModel.setUserId(userId);
		catalogDataModel.setCatalogList(catalogList);
		
		ResponseEntity<CatalogDataModel> response =
				new ResponseEntity<CatalogDataModel>(catalogDataModel, HttpStatus.OK);
	
		return response;
	}
}








