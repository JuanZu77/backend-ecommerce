	package com.juanzubiri.ecommerce.backend.infrastructure.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juanzubiri.ecommerce.backend.application.OrderService;
import com.juanzubiri.ecommerce.backend.domain.model.Order;
import com.juanzubiri.ecommerce.backend.domain.model.OrderState;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/orders")
@Slf4j
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@PostMapping
	public ResponseEntity<Order> save(@RequestBody Order order){
	
		if(order.getOrderState().toString().equals(OrderState.CANCELLED.toString())) {
			order.setOrderState(OrderState.CANCELLED);
		}
		else {
			order.setOrderState(OrderState.CONFIRMED);
		}
		
		return ResponseEntity.ok(orderService.save(order));
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/update/state/order")
	public ResponseEntity updateStateById(@RequestParam Integer id, @RequestParam String state) {
		
		orderService.updateStateById(id, state);
		
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping
	public ResponseEntity<Iterable<Order>> findAll(){
		
		return ResponseEntity.ok(orderService.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.findById(id));
	}
	
	@GetMapping("/by-user/{id}")
	public ResponseEntity<Iterable<Order>> findByUserId(@PathVariable("id") Integer userId){
		
		return ResponseEntity.ok(orderService.findByUserId(userId));
	}
	
}
