package com.juanzubiri.ecommerce.backend.domain.port;

import com.juanzubiri.ecommerce.backend.domain.model.User;

public interface IUserRepository {
	
	//Guardar Usuario
	User save(User user);

	//Obtener usuarios por email
	User findByEmail(String email);
	
	//Buscar por id
	User findById(Integer id);
	
	//Eliminar por id
	//void deleteById(User id);
}
