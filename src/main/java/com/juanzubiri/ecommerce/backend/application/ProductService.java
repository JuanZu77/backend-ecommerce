package com.juanzubiri.ecommerce.backend.application;

import com.juanzubiri.ecommerce.backend.domain.model.Product;
import com.juanzubiri.ecommerce.backend.domain.port.IProductRepository;

public class ProductService {
	
	private final IProductRepository iProductRepository;

	public ProductService(IProductRepository iProductRepository) {
		super();
		this.iProductRepository = iProductRepository;
	}
	
	public Product save(Product product) {
		return this.iProductRepository.save(product);
	}
	
	public Iterable<Product> findAll(){
		return this.iProductRepository.findAll();
	}
	
	public Product findById(Integer id) {
		return this.iProductRepository.findById(id);
	}
	
	public void deleteById(Integer id) {
		this.iProductRepository.deleteById(id);
	}

}
