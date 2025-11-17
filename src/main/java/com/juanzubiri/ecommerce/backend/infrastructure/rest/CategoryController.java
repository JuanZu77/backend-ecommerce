package com.juanzubiri.ecommerce.backend.infrastructure.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanzubiri.ecommerce.backend.application.CategoryService;
import com.juanzubiri.ecommerce.backend.domain.model.Category;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/admin/categories")
@Slf4j //log.info()
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	

	@PostMapping
	public ResponseEntity<Category> save(@RequestBody Category category) {
		log.info("Nombre de la Categoria: {}", category.getName());
		return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Category>> findAll(){
		
		return ResponseEntity.ok(categoryService.finAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Integer id) {
		
		return ResponseEntity.ok(categoryService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) {
		categoryService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
