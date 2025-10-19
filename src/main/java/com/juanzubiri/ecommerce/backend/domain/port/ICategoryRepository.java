package com.juanzubiri.ecommerce.backend.domain.port;

import com.juanzubiri.ecommerce.backend.domain.model.Category;

public interface ICategoryRepository {
	
	Category save(Category category); //Guardar
	
	Iterable<Category> findAll(); //Obtener
	
	Category findById(Integer id);//Obetner por id
	
	void deleteById(Integer id);//Eliminar

}
