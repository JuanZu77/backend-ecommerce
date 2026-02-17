package com.juanzubiri.ecommerce.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class URLPaypalResponse {
	
	private String url;
	
	/* mismo nombre que frontend "url"
	 
	  export class UrlPaymentResponse {
            constructor(public url:String){     
           }
       }
	 
	 */

}
