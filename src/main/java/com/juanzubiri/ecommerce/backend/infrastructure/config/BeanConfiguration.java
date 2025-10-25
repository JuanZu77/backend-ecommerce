
package com.juanzubiri.ecommerce.backend.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.juanzubiri.ecommerce.backend.application.UserService;
import com.juanzubiri.ecommerce.backend.domain.port.IUserRepository;

@Configuration //Definida como clase de configuracion
public class BeanConfiguration {
	
	//como UserServicie tiene un contrsuctor con sus varianles debemos pasarlas
	@Bean
	UserService userService(IUserRepository iUserRepository) {
		
		return new UserService(iUserRepository);
		
	}

}
