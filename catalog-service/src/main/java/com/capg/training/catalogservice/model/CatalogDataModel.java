package com.capg.training.catalogservice.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogDataModel {

	private Integer userId;
	private List<CatalogModel> catalogList;
}
