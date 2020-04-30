package com.capg.training.catalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogModel {

	private Integer movieId;
	private Integer rating;
	private String movieName;
	private String category;
}
