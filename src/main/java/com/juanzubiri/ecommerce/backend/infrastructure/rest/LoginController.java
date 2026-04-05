package com.juanzubiri.ecommerce.backend.infrastructure.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanzubiri.ecommerce.backend.application.UserService;
import com.juanzubiri.ecommerce.backend.domain.model.User;
import com.juanzubiri.ecommerce.backend.infrastructure.dto.JwtClient;
import com.juanzubiri.ecommerce.backend.infrastructure.dto.UserDTO;
import com.juanzubiri.ecommerce.backend.infrastructure.service.JwtService;

@RestController
@RequestMapping("/api/v1/security")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	private AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserService userService;


	public LoginController(AuthenticationManager authenticationManager, JwtService jwtService, UserService useService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.userService = useService;
	}


	@PostMapping("/login")
	public ResponseEntity<JwtClient> login(@RequestBody UserDTO userDTO){
		
		Authentication authentication = authenticationManager.authenticate(
				
				new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password())
				);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user = userService.findByEmail(userDTO.username());
		
		
		String token = jwtService.generateToken(userDTO.username());
		
		JwtClient jwtClient = new JwtClient(user.getId(),token, user.getUserType().toString());
		
		return new ResponseEntity<>(jwtClient,HttpStatus.OK);
	}
}
