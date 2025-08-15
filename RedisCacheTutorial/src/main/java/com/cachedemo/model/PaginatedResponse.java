package com.cachedemo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PaginatedResponse {
	
	private List<Product> productList;
	private int pageNumber;
	private int pageSize;
	private int totalElements;

}
