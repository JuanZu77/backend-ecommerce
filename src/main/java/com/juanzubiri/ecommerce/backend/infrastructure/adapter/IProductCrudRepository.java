package com.juanzubiri.ecommerce.backend.infrastructure.adapter;

import org.springframework.data.repository.CrudRepository;

import com.juanzubiri.ecommerce.backend.infrastructure.entity.ProductEntity;

public interface IProductCrudRepository extends CrudRepository<ProductEntity, Integer>{

}
