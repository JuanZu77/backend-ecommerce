package com.juanzubiri.ecommerce.backend.application;

import com.juanzubiri.ecommerce.backend.domain.model.Order;
import com.juanzubiri.ecommerce.backend.domain.port.IOrderRepository;

public class OrderService {
	
	private final IOrderRepository iOrderReposiotory;

	public OrderService(IOrderRepository iOrderReposiotory) {
		super();
		this.iOrderReposiotory = iOrderReposiotory;
	}
	
    
	public Order save(Order order) {
		return this.iOrderReposiotory.save(order);
	}
	
	public Order findById(Integer id) {
		return this.iOrderReposiotory.findById(id);
	}
	
	public Iterable<Order> findAll(){
		
		return this.iOrderReposiotory.findAll();
	}
	
	public Iterable<Order> findByUserId(Integer userId){
		return this.iOrderReposiotory.findByUserId(userId);
	}
	
	public void updateStateById(Integer id, String state) {
		this.iOrderReposiotory.updateStateById(id, state);
	}

}
