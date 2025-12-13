package com.juanzubiri.ecommerce.backend.infrastructure.adapter;

//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.transaction.annotation.Transactional;

//import com.juanzubiri.ecommerce.backend.domain.model.OrderState;
import com.juanzubiri.ecommerce.backend.infrastructure.entity.OrderEntity;
import com.juanzubiri.ecommerce.backend.infrastructure.entity.UserEntity;

public interface IOrderCrudRepository extends CrudRepository<OrderEntity, Integer>{

	/*
	//polimetros para actualizar estado de la orden por ID
	 @Transactional
	 @Modifying(clearAutomatically = true, flushAutomatically = true)
	 @Query("UPDATE OrderEntity o SET o.orderState = :state WHERE o.id = :id")
	 int updateStateById(@Param("id") Integer id, @Param("state") OrderState state);
 	*/
	
	//Buscar ordenes por usuario
	Iterable<OrderEntity> findByUserEntity(UserEntity userEntity);
}
