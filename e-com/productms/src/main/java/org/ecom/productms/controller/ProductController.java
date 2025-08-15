package org.ecom.productms.controller;

import java.util.List;
import java.util.Optional;

import org.ecom.productms.model.Product;
import org.ecom.productms.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product/")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService service;
	
	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody Product product) {	
		return ResponseEntity.ok(service.saveProduct(product));
	}
	
	@GetMapping

	public ResponseEntity<List<Product>> getAllProducts(){
		return ResponseEntity.ok(service.getAllProducts());
	}
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long id){
		Optional<Product> found=service.getProductById(id);
		return found.map( ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
	}


}
