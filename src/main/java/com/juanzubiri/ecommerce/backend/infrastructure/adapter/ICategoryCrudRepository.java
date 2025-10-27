package com.juanzubiri.ecommerce.backend.infrastructure.adapter;

import org.springframework.data.repository.CrudRepository;

import com.juanzubiri.ecommerce.backend.infrastructure.entity.CategoryEntity;

public interface ICategoryCrudRepository extends CrudRepository<CategoryEntity, Integer>{

}
