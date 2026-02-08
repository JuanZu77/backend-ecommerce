package com.juanzubiri.ecommerce.backend.infrastructure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalService {
	
	private final APIContext apiContext;
	
	public PaypalService(APIContext apiContext) {
		this.apiContext = apiContext;
	}
	
	public Payment createPayment(
			Double total,
			String currency,
			String method,
			String intent,
			String description,
			String cancelUrl,
			String successUrl
			) throws PayPalRESTException {
		
		Amount amount = new Amount();
		amount.setCurrency(currency);
	    amount.setTotal(String.format(Locale.forLanguageTag(currency), "%.2f", total));
		//amount.setTotal(String.format(Locale.US, "%.2f", total)); // recomendado 
		
		Transaction transaction = new Transaction();
		transaction.setDescription(description);
		transaction.setAmount(amount);
		
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		
		Payer payer = new Payer();
		payer.setPaymentMethod(method);
		
		Payment payment = new Payment();
		payment.setIntent(intent);
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		
		//cuando seenvia pago a paypal retorna url con datos
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setReturnUrl(successUrl);
		redirectUrls.setCancelUrl(cancelUrl);
		
		return payment.create(apiContext); // aqui requiere la Exception => throws PayPalRESTException
	}
	
	
	/*
	 Este método es invocado una vez que es aprobado el pago por paypal, recibe como 
	 parámetro el id del pago y el id de la cuenta de quien realiza el pago.
	 */
	public  Payment executePayment(
            String paymentId,
            String payerId

    ) throws PayPalRESTException {

        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        return  payment.execute(apiContext,paymentExecution);

    }   



}
