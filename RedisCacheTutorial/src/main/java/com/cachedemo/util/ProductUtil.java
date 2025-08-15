package com.cachedemo.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cachedemo.model.PaginatedResponse;
import com.cachedemo.model.Product;

@Component
public class ProductUtil {

	public PaginatedResponse paginateList(List<Product> fullList, int pageNum, int pageSize) {
	    int totalItems = fullList.size();
	    int fromIndex = pageNum * pageSize;
	    int toIndex = Math.min(fromIndex + pageSize, totalItems);

	    if (fromIndex > toIndex) {
	        return new PaginatedResponse(List.of(), pageNum, pageSize, totalItems);
	    }

	    List<Product> paginatedList = fullList.subList(fromIndex, toIndex);
	    return new PaginatedResponse(paginatedList, pageNum, pageSize, totalItems);

	}

}
