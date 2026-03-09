package com.juanzubiri.ecommerce.backend.infrastructure.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanzubiri.ecommerce.backend.application.ProductService;
import com.juanzubiri.ecommerce.backend.domain.model.Product;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

	private final ProductService productService;

	public HomeController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Product>> findAll(){
		return ResponseEntity.ok(productService.findAll());
	}
	
}
