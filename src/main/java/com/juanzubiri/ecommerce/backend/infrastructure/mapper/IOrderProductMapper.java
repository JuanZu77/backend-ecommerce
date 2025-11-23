package com.juanzubiri.ecommerce.backend.infrastructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.juanzubiri.ecommerce.backend.domain.model.OrderProduct;
import com.juanzubiri.ecommerce.backend.infrastructure.entity.OrderProductEntity;

@Mapper(componentModel = "spring")
public interface IOrderProductMapper {
	
	@Mappings(
			{
				@Mapping(source = "id", target = "id"),
				@Mapping(source = "quantity", target = "quantity"),
				@Mapping(source = "price", target = "price"),
				@Mapping(source = "productId", target = "productId")
			}
			)

	OrderProduct toOrderProduct(OrderProductEntity orderProductEntity); //Entidad a Dominio
	Iterable<OrderProduct> toOrderProductList(Iterable<OrderProductEntity> orderProductEntities);
	
	@InheritInverseConfiguration
	@Mapping(target = "orderEntity", ignore = true)
	OrderProductEntity toOrderProductEntity(OrderProduct orderProduct); //Dominio a Entidad
}
