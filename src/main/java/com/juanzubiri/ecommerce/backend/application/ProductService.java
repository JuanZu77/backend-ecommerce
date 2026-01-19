package com.juanzubiri.ecommerce.backend.application;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.juanzubiri.ecommerce.backend.domain.model.Product;
import com.juanzubiri.ecommerce.backend.domain.port.IProductRepository;

public class ProductService {
	
	private final IProductRepository iProductRepository;
	
	private final UploadFile uploadFile;

	public ProductService(IProductRepository iProductRepository, UploadFile uploadFile) {
		super();
		this.iProductRepository = iProductRepository;
		this.uploadFile = uploadFile;
	}
	
	public Product save(Product product, MultipartFile multipartFile) throws IOException {
		//validar save or update
		if(product.getId()!=0) { // cuando es producto modificado
			if(multipartFile == null) {
				product.setUrlImage(product.getUrlImage());
			}
			else {
				product.setUrlImage(uploadFile.upload(multipartFile));
			}
		}else { //cuando es Producto nuevo
			product.setUrlImage(uploadFile.upload(multipartFile));
		}
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
