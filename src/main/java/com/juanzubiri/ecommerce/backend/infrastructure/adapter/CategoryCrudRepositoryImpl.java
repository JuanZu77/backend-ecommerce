package com.juanzubiri.ecommerce.backend.infrastructure.adapter;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.juanzubiri.ecommerce.backend.domain.model.Category;
import com.juanzubiri.ecommerce.backend.domain.port.ICategoryRepository;
import com.juanzubiri.ecommerce.backend.infrastructure.entity.CategoryEntity;
import com.juanzubiri.ecommerce.backend.infrastructure.mapper.CategoryMapper;



@Repository                           //..... port.ICategoryRepository;
public class CategoryCrudRepositoryImpl implements ICategoryRepository{
	
	private final ICategoryCrudRepository iCategoryCrudRepository;
	private final CategoryMapper categoryMapper;

	public CategoryCrudRepositoryImpl(ICategoryCrudRepository iCategoryCrudRepository, CategoryMapper categoryMapper) {
		super();
		this.iCategoryCrudRepository = iCategoryCrudRepository;
		this.categoryMapper = categoryMapper;
	}

	@Override
	@Transactional
	public Category save(Category category) {
			
		CategoryEntity entity = categoryMapper.toCategoryEntity(category); // Domain → Entity
		CategoryEntity savedEntity = iCategoryCrudRepository.save(entity); // persistencia
		return categoryMapper.toCategory(savedEntity); //Entity → Domain
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Category> findAll() {
		
		 Iterable<CategoryEntity> entities = iCategoryCrudRepository.findAll(); //busamos todas las entidades
		 return categoryMapper.toCategoryList(entities); //Entity → Domain
	}

	@Override
	@Transactional(readOnly = true)
	public Category findById(Integer id) {
		
		  return iCategoryCrudRepository.findById(id)
		            .map(categoryMapper::toCategory) // Optional<CategoryEntity> → Category
		            .orElse(null); //si no existe
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		
		  iCategoryCrudRepository.deleteById(id);
	}

}
