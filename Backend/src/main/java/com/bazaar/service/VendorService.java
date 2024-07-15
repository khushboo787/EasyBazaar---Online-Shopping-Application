package com.bazaar.service;


import java.io.IOException;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.web.multipart.MultipartFile;

import com.bazaar.exception.PersonalInfoException;
import com.bazaar.exception.ProductException;
import com.bazaar.exception.VendorException;
import com.bazaar.model.Product;
import com.bazaar.model.Vendor;

public interface VendorService {
	
	public String createVendorAccount(Vendor vendor) throws VendorException,PersonalInfoException,IOException;
	
	public String addProduct(Product product,String key) throws ProductException,LoginException,VendorException;
	
	public String updateProduct(Product product, String key) throws ProductException,LoginException,VendorException;
	
	public List<Product> viewAllProductByVendorId(Integer vendorId) throws ProductException,LoginException,VendorException;
	
	public String removeProduct(Integer productId,String key) throws ProductException,LoginException,VendorException;
	
	public Product viewProductById(Integer productId) throws ProductException,LoginException,VendorException;
	
	public Vendor getSingleVendor(String key)throws LoginException;

	 
	
}
