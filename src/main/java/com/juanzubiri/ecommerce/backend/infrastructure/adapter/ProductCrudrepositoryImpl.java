package com.juanzubiri.ecommerce.backend.infrastructure.adapter;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.juanzubiri.ecommerce.backend.domain.model.Product;
import com.juanzubiri.ecommerce.backend.domain.port.IProductRepository;
import com.juanzubiri.ecommerce.backend.infrastructure.entity.ProductEntity;
import com.juanzubiri.ecommerce.backend.infrastructure.mapper.ProductMapper;

@Repository   
public class ProductCrudrepositoryImpl implements IProductRepository{
	
	private final IProductCrudRepository iProductCrudRepository;
	private final ProductMapper productMapper;

	public ProductCrudrepositoryImpl(IProductCrudRepository iProductCrudRepository, ProductMapper productMapper) {
		super();
		this.iProductCrudRepository = iProductCrudRepository;
		this.productMapper = productMapper;
	}

	@Override
	@Transactional
	public Product save(Product product) {
		// TODO Auto-generated method stub

		ProductEntity entity = productMapper.toProductEntity(product); //Domain a Entity
		ProductEntity savedEntity = iProductCrudRepository.save(entity);
		return productMapper.toProduct(savedEntity); //Entity a Domain
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Product> findAll() {
		// TODO Auto-generated method stub

		Iterable<ProductEntity> entities = iProductCrudRepository.findAll();
		return productMapper.toProductList(entities); //entity a domain
		
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Integer id) {
		// TODO Auto-generated method stub

		 return iProductCrudRepository.findById(id)
				 .map(productMapper::toProduct) //entity a domain
				 .orElse(null);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
		iProductCrudRepository.deleteById(id);
		
	}

}
