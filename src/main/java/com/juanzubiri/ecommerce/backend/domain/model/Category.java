package com.juanzubiri.ecommerce.backend.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	//Atributos
	private Integer id;
	private String name;
	private LocalDateTime dateCreated;
	private LocalDateTime dateUpdated;

}
