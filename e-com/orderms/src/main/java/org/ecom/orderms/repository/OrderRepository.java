package org.ecom.orderms.repository;

import java.util.List;

import org.ecom.orderms.dto.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long>{

	List<Order> findByUserId(Long userId);
}
