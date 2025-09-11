package org.ecom.productms.service;

import java.util.List;
import java.util.Optional;

import org.ecom.productms.dao.ProductRepository;
import org.ecom.productms.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


	private ProductRepository repository;
	

	public ProductService(ProductRepository repository) {
		super();
		this.repository = repository;
	}

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
