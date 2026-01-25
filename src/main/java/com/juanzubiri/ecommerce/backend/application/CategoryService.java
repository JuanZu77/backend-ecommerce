package com.juanzubiri.ecommerce.backend.application;

import com.juanzubiri.ecommerce.backend.domain.model.Category;
import com.juanzubiri.ecommerce.backend.domain.port.ICategoryRepository;

public class CategoryService {

	private final ICategoryRepository iCategoryRepository;

	public CategoryService(ICategoryRepository iCategoryRepository) {
		super();
		this.iCategoryRepository = iCategoryRepository;
	}
	
	
	/*public Category save (Category category) {
		return iCategoryRepository.save(category);
	}*/
	
	public Category save(Category category) {

	    // UPDATE
	    if (category.getId() != null) {
	        Category existing = iCategoryRepository.findById(category.getId());
	        // si tu repo devuelve Optional, se cambia por orElseThrow

	        if (existing == null) {
	            throw new RuntimeException("Category not found");
	        }

	        // conservar datos que no querés pisar si vinieran null
	        // (por ahora solo name)
	        if (category.getName() == null || category.getName().trim().isEmpty()) {
	            category.setName(existing.getName());
	        }

	        return iCategoryRepository.save(category);
	    }

	    // CREATE (sin id)
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
