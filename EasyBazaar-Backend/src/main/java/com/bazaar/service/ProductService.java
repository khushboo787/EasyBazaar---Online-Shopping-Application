package com.bazaar.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.bazaar.enums.CategoryType;
import com.bazaar.exception.CustomerException;
import com.bazaar.exception.ProductException;
import com.bazaar.model.Product;

public interface ProductService {
	
	public Product getSingalProduct(String key,Integer productId)throws LoginException,ProductException;
	
	public List<Product> viewAllProduct() throws ProductException,LoginException,CustomerException;
	
	public List<Product> getProductByCategory(CategoryType category,String key) throws ProductException,LoginException,CustomerException; 

}
