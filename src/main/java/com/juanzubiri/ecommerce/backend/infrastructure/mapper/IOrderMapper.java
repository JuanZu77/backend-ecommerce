package com.juanzubiri.ecommerce.backend.infrastructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.juanzubiri.ecommerce.backend.domain.model.Order;
import com.juanzubiri.ecommerce.backend.infrastructure.entity.OrderEntity;

@Mapper(componentModel = "spring", uses = IOrderProductMapper.class)
public interface IOrderMapper {

	
	@Mappings(
			{
				@Mapping(source = "id", target = "id"),
				@Mapping(source = "dateCreated", target = "dateCreated"),
				@Mapping(source = "orderState", target = "orderState"),
				@Mapping(source = "userEntity.id", target = "userId"),
				@Mapping(source = "orderProducts", target = "orderProducts")
			}
			)
	
	Order toOrder(OrderEntity orderEntity); //Entidad a Dominio
	Iterable<Order> toOrderList(Iterable<OrderEntity> orderEntities);
	
	@InheritInverseConfiguration
	OrderEntity toOrderEntity (Order order); //Dominio a Entidad
	
}
