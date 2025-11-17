
package com.juanzubiri.ecommerce.backend.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.juanzubiri.ecommerce.backend.application.CategoryService;
import com.juanzubiri.ecommerce.backend.application.ProductService;
import com.juanzubiri.ecommerce.backend.application.UserService;
import com.juanzubiri.ecommerce.backend.domain.port.ICategoryRepository;
import com.juanzubiri.ecommerce.backend.domain.port.IProductRepository;
import com.juanzubiri.ecommerce.backend.domain.port.IUserRepository;

@Configuration //Definida como clase de configuracion
public class BeanConfiguration {
	
	//como UserServicie tiene un contrsuctor con sus variables, por lo que debemos pasar como parametros
	@Bean
	UserService userService(IUserRepository iUserRepository) {
		
		return new UserService(iUserRepository);
	}
	
	
	@Bean
	CategoryService categoryService(ICategoryRepository iCategoryRepository) {
		
		return new CategoryService(iCategoryRepository);
	}
	
	@Bean
	ProductService productService(IProductRepository iProductRepository) {
		
		return new ProductService(iProductRepository);
	}

}
