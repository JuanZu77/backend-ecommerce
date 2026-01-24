package com.juanzubiri.ecommerce.backend.application;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.juanzubiri.ecommerce.backend.domain.model.Product;
import com.juanzubiri.ecommerce.backend.domain.port.IProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductService {
	
	private final IProductRepository iProductRepository;
	
	private final UploadFile uploadFile;

	public ProductService(IProductRepository iProductRepository, UploadFile uploadFile) {
		super();
		this.iProductRepository = iProductRepository;
		this.uploadFile = uploadFile;
	}
	

	public Product save(Product product, MultipartFile multipartFile) throws IOException {

	    if (product.getId() != null) { // UPDATE
	        Product existing = iProductRepository.findById(product.getId());
	        // Si tu repo devuelve Optional, ajustar con orElseThrow

	        if (multipartFile != null && !multipartFile.isEmpty()) {

	            // borrar imagen anterior (si no es default)
	            String oldName = extractFilename(existing.getUrlImage());
	            if (oldName != null && !oldName.equalsIgnoreCase("default.jpg")) {
	                uploadFile.delete(oldName);
	            }

	            // subir nueva imagen y asignar url
	            product.setUrlImage(uploadFile.upload(multipartFile));

	        } else {
	            // si no viene nueva imagen, conservar la actual
	            product.setUrlImage(existing.getUrlImage());
	        }

	        return iProductRepository.save(product);
	    }

	    // CREATE
	    product.setUrlImage(uploadFile.upload(multipartFile));
	    return iProductRepository.save(product);
	}

	
	
	
	public Iterable<Product> findAll(){
		return this.iProductRepository.findAll();
	}
	
	public Product findById(Integer id) {
		return this.iProductRepository.findById(id);
	}
	
	
	
	public void deleteById(Integer id) {
	    Product product = findById(id);

	    String name = extractFilename(product.getUrlImage());
	    if (name != null && !name.equalsIgnoreCase("default.jpg")) {
	        uploadFile.delete(name);
	    }

	    iProductRepository.deleteById(id);
	}
	
	
	
	private String extractFilename(String url) {
	    if (url == null) return null;
	    int idx = url.lastIndexOf('/');
	    return (idx >= 0) ? url.substring(idx + 1) : url;
	}



}
