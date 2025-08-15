package com.cachedemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cachedemo.dao.ProductRepository;
import com.cachedemo.exception.GlobalExceptionHandler;
import com.cachedemo.model.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final GlobalExceptionHandler globalExceptionHandler;
	
	private final ProductRepository repository;

	// save
	@CachePut(value="products" )
    public Product saveProduct(Product product) {
        return repository.save(product);
    }
    
    @Cacheable(value = "products")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    
	@Cacheable(value = "product", key = "#id")
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }
	
	@CacheEvict(value = "product", key="#id")
	public void deleteById(long id) {
		repository.deleteById(id);
	}
	
	public Page<Product> getProductsbyPage(int pageNumber,int pageSize){
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<Product>pageList=repository.findAll(pageable);
		return pageList;
	}

    
 

}
