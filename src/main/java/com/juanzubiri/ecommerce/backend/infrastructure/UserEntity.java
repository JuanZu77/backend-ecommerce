package com.juanzubiri.ecommerce.backend.infrastructure;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.juanzubiri.ecommerce.backend.domain.model.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //jakarta persistence
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
	
	@Id //jakarta persistence
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// @Column(name="username") si quisera cambiar el nombre de la columna
	private String username;
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	private String address;
	private String cellphone;
	private String password;
	
	@Enumerated(EnumType.STRING) //mapearla de enum a string para almacenar en la BD
	private UserType userType;
	
	@CreationTimestamp //Guarda la fecha y hora sin hacer nada de codigo
	private LocalDateTime dateCreated;
	@UpdateTimestamp
	private LocalDateTime dateUpdated;

}
