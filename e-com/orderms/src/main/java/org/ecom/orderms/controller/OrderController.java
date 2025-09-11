package org.ecom.orderms.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ecom.orderms.dto.Order;
import org.ecom.orderms.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final WebClient client;

    public OrderController(OrderService orderService, WebClient client) {
        this.orderService = orderService;
		this.client = client;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }
    @GetMapping
    public List<Order>getAllOrders(){
    	return orderService.allOrders();
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }
    @GetMapping("/order/products")
    public String getUserProduct(){
		return client.
				get().
				retrieve().
				bodyToMono(String.class).block();
    }
    
    @GetMapping("/productorder/{id}")
    public String getOrderProductById(@PathVariable Long id) {
		 String block = client.get().uri("/{id}",id).retrieve().bodyToMono(String.class).block();
		 return block;
    }
}

