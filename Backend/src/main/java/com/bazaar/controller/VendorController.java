package com.bazaar.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bazaar.exception.PersonalInfoException;
import com.bazaar.exception.ProductException;
import com.bazaar.exception.VendorException;
import com.bazaar.model.Product;
import com.bazaar.model.Vendor;
import com.bazaar.service.FileService;
import com.bazaar.service.VendorService;

@RestController
@RequestMapping("/vendorController")
public class VendorController {
	
	
	@Autowired
	private VendorService vendorService;
	
	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to easyBazaar";
	}
	
	
	@PostMapping("/vendor")
	public ResponseEntity<String> createVendorAccount(@Valid @RequestBody Vendor vendor) throws VendorException,PersonalInfoException, IOException{
		
 	
		String outPut = vendorService.createVendorAccount(vendor);
		return new ResponseEntity<String>(outPut,HttpStatus.CREATED);
	}
	
	@PostMapping("/addproduct/{key}")
	public ResponseEntity<String> addProduct(@RequestBody Product product,@PathVariable("key") String key )throws ProductException, LoginException, VendorException{
		
		String outPut = vendorService.addProduct(product,key);
		return new ResponseEntity<String>(outPut,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteproduct/{productId}/{key}")
	public ResponseEntity<String> removeProduct(@PathVariable("productId") Integer productId,@PathVariable("key") String key )throws ProductException, LoginException, VendorException{
		
		String outPut = vendorService.removeProduct(productId,key);
		return new ResponseEntity<String>(outPut,HttpStatus.OK);
	}
	
	@PutMapping("/updateproduct/{key}")
	public ResponseEntity<String> updateProduct(@RequestBody Product product,@PathVariable("key") String key )throws ProductException, LoginException, VendorException{
		
		String outPut = vendorService.updateProduct(product,key);
		return new ResponseEntity<String>(outPut,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewallproducts/{vendorId}")
	public ResponseEntity<List<Product>> viewAllProduct(@PathVariable("vendorId") Integer vendorId)throws ProductException, LoginException, VendorException{
		
		List<Product> outPut= vendorService.viewAllProductByVendorId(vendorId);
		return new ResponseEntity<List<Product>>(outPut,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewproduct/{productId}")
	public ResponseEntity<Product> viewProduct(@PathVariable("productId") Integer productId)throws ProductException, LoginException, VendorException{
		
		Product outPut= vendorService.viewProductById(productId);
		return new ResponseEntity<Product>(outPut,HttpStatus.CREATED);
	}
	
	@GetMapping("/singalvendor/{key}")
	public ResponseEntity<Vendor> getSingleVendor(@PathVariable("key") String key) throws LoginException{
		
		Vendor vendor = vendorService.getSingleVendor(key);
		return new ResponseEntity<Vendor>(vendor, HttpStatus.ACCEPTED);
		
	}

}
