package com.juanzubiri.ecommerce.backend.infrastructure.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.juanzubiri.ecommerce.backend.domain.model.DataPayment;
import com.juanzubiri.ecommerce.backend.domain.model.URLPaypalResponse;
import com.juanzubiri.ecommerce.backend.infrastructure.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/v1/payments")
public class PaypalController {
	
	private final PaypalService paypalService;
	private final String SUCCESS_URL = "http://localhost:8080/api/v1/payments/success";
	private final String CANCEL_URL = "http://localhost:8080/api/v1/payments/cancel";
	
	@PostMapping
	public URLPaypalResponse createPayment(@RequestBody DataPayment dataPayment) {
		try {
			Payment payment = paypalService.createPayment(
					Double.valueOf(dataPayment.getAmount()),
					dataPayment.getCurrency(),
					dataPayment.getMethod(),
					"SALE",
					dataPayment.getDescription(),
					CANCEL_URL,
					SUCCESS_URL
					);
			
			for(Links links : payment.getLinks()) {
				if(links.getRel().equals("approval_url")) {
					return new URLPaypalResponse(links.getHref());
				}
			}
		} catch (NumberFormatException | PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new URLPaypalResponse("http://localhost:4200");
	}
	
	
	@GetMapping("/success")
	public RedirectView paymentSuccess(@RequestParam("paymentId") String paymentId, 
			                           @RequestParam("PayerID") String payerId) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")) {
				//return new RedirectView("http:localhost:4200/payment/success");
				return new RedirectView("http://localhost:4200");
			}
			
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new RedirectView("http://localhost:4200");
	}
	
	
	@GetMapping("/cancel")
	public RedirectView paymentCancel() {
		
		return new RedirectView("http://localhost:4200");
	}
	
	

}
