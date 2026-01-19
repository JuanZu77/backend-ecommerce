package com.juanzubiri.ecommerce.backend.infrastructure.rest;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<Product> save(
		                             	@RequestParam("code") String code,
		 	                            @RequestParam("name") String name,
			                            @RequestParam("description") String description,
			                            @RequestParam("price") BigDecimal price,
			                            @RequestParam("urlImage") String urlImage,
			                            @RequestParam("userId") Integer userId,
			                            @RequestParam("categoryId") Integer categoryId,
			                            @RequestParam(value = "image", required = false) MultipartFile multipartFile
			                            ) throws IOException{
		
		Product product = new Product();
		product.setCode(code);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setUrlImage(urlImage);
		product.setUserId(userId);
		product.setCategoryId(categoryId);
		
		log.info("Nombre Producto: {}", product.getName());
		return new ResponseEntity<>(productService.save(product, multipartFile), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes = "multipart/form-data")
	public ResponseEntity<Product> update(
	    @PathVariable Integer id,
	    @RequestParam("code") String code,
	    @RequestParam("name") String name,
	    @RequestParam("description") String description,
	    @RequestParam("price") BigDecimal price,
	    @RequestParam("urlImage") String urlImage,
	    @RequestParam("userId") Integer userId,
	    @RequestParam("categoryId") Integer categoryId,
	    @RequestParam(value = "image", required = false) MultipartFile multipartFile
	) throws IOException {

	    Product product = new Product();
	    product.setId(id);
	    product.setCode(code);
	    product.setName(name);
	    product.setDescription(description);
	    product.setPrice(price);
	    product.setUrlImage(urlImage);
	    product.setUserId(userId);
	    product.setCategoryId(categoryId);

	    return ResponseEntity.ok(productService.save(product, multipartFile));
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
