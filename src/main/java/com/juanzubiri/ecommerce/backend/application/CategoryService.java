package com.juanzubiri.ecommerce.backend.application;

import com.juanzubiri.ecommerce.backend.domain.model.Category;
import com.juanzubiri.ecommerce.backend.domain.port.ICategoryRepository;

public class CategoryService {

	private final ICategoryRepository iCategoryRepository;

	public CategoryService(ICategoryRepository iCategoryRepository) {
		super();
		this.iCategoryRepository = iCategoryRepository;
	}
	
	
	public Category save (Category category) {
		return iCategoryRepository.save(category);
	}
	
	public Iterable<Category> finAll(){
		return iCategoryRepository.findAll();
	}
	
	public Category findById(Integer id) {
		return iCategoryRepository.findById(id);
	}
	
	public void deleteById(Integer id) {
		iCategoryRepository.deleteById(id);
	}
}
