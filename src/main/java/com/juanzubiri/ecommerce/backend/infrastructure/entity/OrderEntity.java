package com.juanzubiri.ecommerce.backend.infrastructure.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.juanzubiri.ecommerce.backend.domain.model.OrderState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@CreationTimestamp
	private LocalDateTime dateCreated;
	
	@Enumerated(value = EnumType.STRING)
	private OrderState orderState;
	
	@ManyToOne // muchas ordenes para un usuario
	private UserEntity userEntity;
	
	@OneToMany(mappedBy = "orderEntity", cascade = CascadeType.PERSIST) // una orden muchos productos
	private List<OrderProductEntity> orderProducts;

}
