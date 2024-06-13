package com.bazaar.controller;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazaar.exception.PaymentException;
import com.bazaar.exception.ShipperException;
import com.bazaar.exception.VendorException;
import com.bazaar.model.OrderClass;
import com.bazaar.model.Shipper;
import com.bazaar.service.ShipperService;

@RestController
@RequestMapping("/shipperController")
public class ShipperController {

	@Autowired
	private ShipperService shipperService;
	
	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to easyBazaar";
	}
	
	@PostMapping("/assignordertoshipper/{orderId}/{paymentId}/{key}")
	public ResponseEntity<OrderClass> createVendorAccount(@RequestBody Shipper shipper,
			@PathVariable("orderId") Integer orderId,@PathVariable("paymentId") Integer paymentId,@PathVariable("key") String key) throws
	                               ShipperException, LoginException, VendorException, PaymentException{
		
		OrderClass outPut = shipperService.assignOrderToshipper(shipper,orderId,paymentId,key);
		return new ResponseEntity<OrderClass>(outPut,HttpStatus.CREATED);
	}
}
