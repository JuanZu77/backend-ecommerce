package com.juanzubiri.ecommerce.backend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Order {
	
	private Integer id;
	private LocalDateTime dateCreated;
	private List<OrderProduct> orderProducts;
	private OrderState orderState;
	private Integer userId;
	
	public Order() {
		orderProducts = new ArrayList<>();
	}
	
	//calculo del Total
	public BigDecimal getTotalOrderPrice() {
		return this.orderProducts.stream()
				.map(ordenProduct -> ordenProduct.getTotalItem())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
