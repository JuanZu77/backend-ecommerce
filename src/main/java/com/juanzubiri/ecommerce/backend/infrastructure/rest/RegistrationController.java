package com.juanzubiri.ecommerce.backend.infrastructure.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanzubiri.ecommerce.backend.application.RegistrationService;
import com.juanzubiri.ecommerce.backend.domain.model.User;

@RestController
@RequestMapping("/api/v1/security")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {
	
	private final RegistrationService registrationService;
	private final BCryptPasswordEncoder passwordEncoder;

	public RegistrationController(RegistrationService registrationService, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.registrationService = registrationService;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return new ResponseEntity<User>(registrationService.register(user), HttpStatus.CREATED);
  }
	
	
}
