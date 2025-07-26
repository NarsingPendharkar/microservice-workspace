package org.ecom.productms.service;

import java.util.List;
import java.util.Optional;

import org.ecom.productms.dao.ProductRepository;
import org.ecom.productms.exception.GlobalExceptionHandler;
import org.ecom.productms.model.Product;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final GlobalExceptionHandler globalExceptionHandler;
	
	private final ProductRepository repository;

	// save
	
    public Product saveProduct(Product product) {
        return repository.save(product);
    }
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    
 

}
