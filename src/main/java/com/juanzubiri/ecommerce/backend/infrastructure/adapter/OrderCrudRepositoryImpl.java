package com.juanzubiri.ecommerce.backend.infrastructure.adapter;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.juanzubiri.ecommerce.backend.domain.model.Order;
import com.juanzubiri.ecommerce.backend.domain.model.OrderState;
import com.juanzubiri.ecommerce.backend.domain.port.IOrderRepository;
import com.juanzubiri.ecommerce.backend.infrastructure.entity.OrderEntity;
import com.juanzubiri.ecommerce.backend.infrastructure.entity.UserEntity;
import com.juanzubiri.ecommerce.backend.infrastructure.mapper.IOrderMapper;

@Repository
public class OrderCrudRepositoryImpl implements IOrderRepository{
	
	private final IOrderMapper iOrderMapper;
	private final IOrderCrudRepository iOrderCrudRepository;

	public OrderCrudRepositoryImpl(IOrderMapper iOrderMapper, IOrderCrudRepository iOrderCrudRepository) {
		super();
		this.iOrderMapper = iOrderMapper;
		this.iOrderCrudRepository = iOrderCrudRepository;
	}

	
	@Override
	@Transactional
	public Order save(Order order) {
	
		 // Dominio → Entidad
	    OrderEntity orderEntity = iOrderMapper.toOrderEntity(order);

	    // Setear padre en cada OrderProductEntity
	    orderEntity.getOrderProducts()
	               .forEach(op -> op.setOrderEntity(orderEntity));

	    // Guardar orden + productos
	    OrderEntity saved = iOrderCrudRepository.save(orderEntity);

	    // Entidad → Dominio
	    return iOrderMapper.toOrder(saved);
	}

	
	@Override
	@Transactional(readOnly = true)
	public Order findById(Integer id) {

		   return iOrderCrudRepository.findById(id)
		            .map(iOrderMapper::toOrder)  // Entidad → Dominio
		            .orElse(null);    
	}

	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Order> findAll() {

		Iterable<OrderEntity> entities = iOrderCrudRepository.findAll();
		
		return iOrderMapper.toOrderList(entities);
		
	}

	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Order> findByUserId(Integer userId) {
	
	    // Instanciamos UserEntity solo con el ID para usar el método de JPA
	    UserEntity user = new UserEntity();
	    user.setId(userId);

	    // Consultamos la BD
	    Iterable<OrderEntity> entities = iOrderCrudRepository.findByUserEntity(user);

	    // Convertimos a dominio
	    return iOrderMapper.toOrderList(entities);
		
	}

	
	@Override
    @Transactional
	public void updateStateById(Integer id, String state) {
		
		// 1. Buscar la orden en BD
	    OrderEntity orderEntity = iOrderCrudRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

	    // 2. Convertir el String al enum del dominio
	    OrderState newState = OrderState.valueOf(state.toUpperCase());

	    // 3. Setear el nuevo estado
	    orderEntity.setOrderState(newState);

	    // 4. Guardar la entidad actualizada
	    iOrderCrudRepository.save(orderEntity);
		
	}

}
