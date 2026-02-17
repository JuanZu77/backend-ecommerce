package com.juanzubiri.ecommerce.backend.domain.model;

import lombok.Data;

@Data
public class DataPayment {
	
	//informacion del pago
	private String method;
	private String amount;
	private String currency;
	private String description;

}
