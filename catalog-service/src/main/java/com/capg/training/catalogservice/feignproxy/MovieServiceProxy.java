package com.capg.training.catalogservice.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capg.training.catalogservice.model.MovieModel;



// need to tell which MS we want to talk
// @FeignClient(<name of the microservice>)
// @FeignClient will create a bean for proxy
@FeignClient(name = "movie-service")
// configure calls for load balance
@RibbonClient(name = "movie-service")
public interface MovieServiceProxy {
	// actual rest-endpoint to call
	@GetMapping("/movie/{movieId}")
	public MovieModel movieDetail(@PathVariable Integer movieId);
	
	// add method signature of more REST ENDPOINT 

}
