package com.juanzubiri.ecommerce.backend.infrastructure.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanzubiri.ecommerce.backend.infrastructure.dto.UserDTO;

@RestController
@RequestMapping("/api/v1/security")
public class LoginController {
	
	private AuthenticationManager authenticationManager;

	public LoginController(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
		
		Authentication authentication = authenticationManager.authenticate(
				
				new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password())
				);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<String>("Usuario Logueado satisfactoriamente",HttpStatus.OK);
	}
}
