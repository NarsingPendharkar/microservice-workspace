package org.ecom.productms.dao;

import java.util.Optional;

import org.ecom.productms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	public Optional<Product> findById(long id);
}
