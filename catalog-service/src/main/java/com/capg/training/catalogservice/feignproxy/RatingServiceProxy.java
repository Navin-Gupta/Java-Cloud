package com.capg.training.catalogservice.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capg.training.catalogservice.model.RatingDataModel;


@FeignClient(name = "rating-service")
@RibbonClient(name = "rating-service")
public interface RatingServiceProxy {
	@GetMapping("/rating/{userId}")
	public RatingDataModel getRatings(@PathVariable Integer userId);
}
