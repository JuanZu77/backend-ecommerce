package com.juanzubiri.ecommerce.backend.infrastructure.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanzubiri.ecommerce.backend.application.UserService;
import com.juanzubiri.ecommerce.backend.domain.model.User;

@RestController
@RequestMapping("/api/v1/users") //http://localhost:8085/api/v1/users
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping
	public User save(@RequestBody User user) {
		
		return userService.save(user);
	}
	
	@GetMapping
	public User findById(@PathVariable Integer id) {
		
		return userService.findById(id);
	}
	

}
