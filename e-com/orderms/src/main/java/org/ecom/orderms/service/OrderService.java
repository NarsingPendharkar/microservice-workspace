package org.ecom.orderms.service;


import java.util.List;

import org.ecom.orderms.dto.Order;
import org.ecom.orderms.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        // TODO: call UserMS and ProductMS to validate user and productIds
        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

	public List<Order> allOrders() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}
}

