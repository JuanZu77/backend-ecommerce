package com.juanzubiri.ecommerce.backend.domain.port;

import com.juanzubiri.ecommerce.backend.domain.model.Product;

public interface IProductRepository {

	//Guardar productos
	Product save (Product product);
	
	//Obtener todos los productos registrados
	Iterable<Product> findAll(); //Colleccion de Tipo Product
	
	//Obetenr producto por ID
	Product findById(Integer id);
	
	//Eliminar Product
	void deleteById(Integer id);
}
