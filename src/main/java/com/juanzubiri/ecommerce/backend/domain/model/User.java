package com.juanzubiri.ecommerce.backend.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	//Atributos
	private Integer id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String cellphone;
	private String password;
	private UserType userType;
	private LocalDateTime dateCreated;
	private LocalDateTime dateUpdated;
	
}
