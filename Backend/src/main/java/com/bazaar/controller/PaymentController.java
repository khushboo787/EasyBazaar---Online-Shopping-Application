package com.bazaar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazaar.exception.CustomerException;
import com.bazaar.exception.OrderClassException;
import com.bazaar.exception.PaymentException;
import com.bazaar.model.Payment;
import com.bazaar.service.PaymentService;

@RestController
@RequestMapping("/paymentController")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to easyBazaar";
	}

	@PostMapping(value = "/giveyourpayment/{orderId}")
	public ResponseEntity<Payment> createPaymentHandler(@RequestBody Payment payment,
			@PathVariable("orderId") Integer orderId) throws PaymentException, CustomerException, OrderClassException{
		
		Payment outPut = paymentService.giveYourPayment(payment,orderId);
		return new ResponseEntity<Payment>(outPut,HttpStatus.CREATED);
		
	}

}
