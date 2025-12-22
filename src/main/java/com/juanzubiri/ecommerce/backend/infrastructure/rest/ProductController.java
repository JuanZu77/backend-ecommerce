package com.juanzubiri.ecommerce.backend.infrastructure.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanzubiri.ecommerce.backend.application.ProductService;
import com.juanzubiri.ecommerce.backend.domain.model.Product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/admin/products")
@Slf4j //ver los logs
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<Product> save(@RequestBody Product product){
		log.info("Nombre Producto: {}", product.getName());
		return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Product>> findAll(){
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Integer id){
		return ResponseEntity.ok(productService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id){
		productService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
