package com.bazaar.controller;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazaar.exception.CartException;
import com.bazaar.exception.CustomerException;
import com.bazaar.exception.OrderClassException;
import com.bazaar.exception.ProductException;
import com.bazaar.model.OrderClass;
import com.bazaar.service.OrderService;

@RestController
@RequestMapping("/orderController")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to shopeasy";
	}
	
	@PutMapping("/giveyourorder/{customerId}")
	public ResponseEntity<OrderClass> giveYourOrderHandler(@RequestBody OrderClass orders,@PathVariable("customerId") Integer customerId) throws LoginException, CustomerException, CartException, ProductException, OrderClassException{

		OrderClass outPut = orderService.giveYourOrder(orders, customerId);

		return new ResponseEntity<OrderClass>(outPut, HttpStatus.OK);

	}
	
	@DeleteMapping("/cancelyourOrder/{orderId}/{key}")
	public ResponseEntity<OrderClass> cancelOrderHandler(@PathVariable Integer orderId,@PathVariable("key") String key) throws LoginException, CustomerException, CartException, ProductException, OrderClassException{

		OrderClass outPut  = orderService.cancelYourOrder(orderId, key);

		return new ResponseEntity<OrderClass>(outPut, HttpStatus.OK);
	}

}
